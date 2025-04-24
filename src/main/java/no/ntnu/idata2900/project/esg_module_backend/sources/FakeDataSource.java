package no.ntnu.idata2900.project.esg_module_backend.sources;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import no.ntnu.idata2900.project.esg_module_backend.dtos.ShipDto;
import no.ntnu.idata2900.project.esg_module_backend.models.DataPoint;
import no.ntnu.idata2900.project.esg_module_backend.models.Position;
import no.ntnu.idata2900.project.esg_module_backend.models.Ship;
import org.springframework.stereotype.Component;

/**
 * The FakeDataSource class simulates a data source to produce boat-related data at intervals.
 * It implements the DataSource interface and sends generated ship data to a DataListener.
 * This class is primarily designed for testing or simulation purposes where no actual data source exists.
 *
 * @author Group 14
 * @version v0.1.1 (2025.04.24)
 */
@Component
public class FakeDataSource implements DataSource {
  private ScheduledExecutorService scheduler;
  private DataListener listener;
  private int i = 0;

  private ShipDto fakeBoatData;
  private long ts;
  private ZoneOffset offset;
  private Random random = new Random();

  /**
   * Starts the fake data source to simulate real-time ship data generation and communication.
   * This method initializes required variables, sets up a periodic task using a timer,
   * and sends generated data to the assigned {@link DataListener} at a fixed interval.
   *
   * <ul>
   * The method performs the following actions:
   * - Initializes the initial ship data using {@code createInitialShipData()}.
   * - Configures a periodic job via {@code TimerTask} to generate and send simulated ship data.
   * - Propagates data to a registered listener through {@code listener.onDataReceived()} if a listener is present.
   * - Stops data generation automatically after 50 cycles.
   * - Logs a message indicating that the fake data source has started.
   * <hr>
   * Notes:
   * - Data is generated in intervals of 15 seconds.
   * - Timestamp is incremented per generation to maintain continuity.
   * - A listener must be set using {@link #setDataListener(DataListener)} to receive data.
   * - Simulated ship data is sent periodically to the registered listener.
   * - The timer and task terminate after completing the defined data transmission cycles.
   * <hr>
   * See also:
   * - {@link #stop()} for stopping the data generation process manually.
   * - {@code createInitialShipData()} and {@code generateBoatData()} for data generation logic.
   */
  @Override
  public void start() {
    ts = 1739438130;
    // Add time zone offset to timestamp
    offset = ZoneOffset.ofHours(1);

    fakeBoatData = createInitialShipData();

    // Make sure any existing scheduler is shut down
    if (scheduler != null && !scheduler.isShutdown()) {
      scheduler.shutdownNow();
    }

    scheduler = Executors.newSingleThreadScheduledExecutor();

    scheduler.scheduleAtFixedRate(() -> {
      if (i < 50) {
        if (listener != null) {
          fakeBoatData = generateBoatData();
          listener.onDataReceived(fakeBoatData);
        }
        i = (i + 1);
      } else {
        stop();
      }
    }, 0, 15, TimeUnit.SECONDS);

    System.out.println("FakeDataSource started");
  }

  /**
   * Creates and initializes a ship data object containing information about a ship's
   * current state, position, and timestamp.
   *
   * @return A {@link ShipDto} object representing the initial ship data.
   */
  private ShipDto createInitialShipData() {
    DataPoint dp = new DataPoint(new Position(61.6031484f, 5.0445668f), ts);

    dp.setShip(new Ship(1, "Ship1", 90.0f, 85.0f, 12.5f, 500.0f, 100.0f));

    return new ShipDto(
        dp.getShip().getId(),
        dp.getShip().getName(),
        dp.getShip().getHeading(),
        dp.getShip().getCourse(),
        dp.getShip().getSpeed(),
        dp.getShip().getFuelLevel(),
        dp.getShip().getFishAmount(),
        0.0f,
        dp.getPos().getLat(),
        dp.getPos().getLng(),
        // UNIX timestamp in seconds
        ZonedDateTime
            .of(
                LocalDateTime.ofEpochSecond(dp.getTs(), 0, offset),
                ZoneId.ofOffset("UTC", offset)
            )
            .format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
    );
  }

  /**
   * Generates a new {@link ShipDto} object with updated data including position, timestamp,
   * fuel level, fish amount, and total distance. The method simulates real-time changes in
   * ship data by slightly modifying the latitude, longitude, and other attributes based on
   * random values and fixed increments.
   *
   * @return A {@link ShipDto} object representing the updated ship data with simulated changes.
   */
  private ShipDto generateBoatData() {
    float lat = fakeBoatData.getLat() + (random.nextFloat() * 0.01f - 0.005f);
    float lng = fakeBoatData.getLng() + (random.nextFloat() * 0.01f - 0.005f);
    // Increment timestamp by 1 minute
    ts += 60;
    float fishAmount = fakeBoatData.getFishAmount() + (random.nextFloat() * 10);
    float fuelLevel = fakeBoatData.getFuelLevel() - (random.nextFloat() * 4);
    float totalDistance = fakeBoatData.getTotalDistance() + (random.nextFloat() * 10);

    DataPoint dp = new DataPoint(new Position(lat, lng), ts);

    dp.setShip(
        new Ship(1, "Ship1", 90.0f, 85.0f, 12.5f, fuelLevel, fishAmount)
    );

    return new ShipDto(
        dp.getShip().getId(),
        dp.getShip().getName(),
        dp.getShip().getHeading(),
        dp.getShip().getCourse(),
        dp.getShip().getSpeed(),
        dp.getShip().getFuelLevel(),
        dp.getShip().getFishAmount(),
        totalDistance,
        dp.getPos().getLat(),
        dp.getPos().getLng(),
        // UNIX timestamp in seconds
        ZonedDateTime
            .of(
                LocalDateTime.ofEpochSecond(dp.getTs(), 0, offset),
                ZoneId.ofOffset("UTC", offset)
            )
            .format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
    );
  }

  /**
   * Stops the fake data source and terminates any ongoing tasks or scheduled operations.
   * <hr>
   * This method performs the following actions:
   * - Shuts down the scheduler immediately if it is initialized and running.
   * - Resets the scheduler reference to null.
   * - Resets the internal iteration counter to zero.
   * - Logs a message indicating that the fake data source has been stopped.
   * <hr>
   * Notes:
   * - Calling this method ensures that no further data generation or dispatching occurs.
   * - It is typically used to clean up after the {@link #start()} method has initiated the data source.
   * - Repeated calls to this method after stopping the scheduler have no additional effect.
   */
  @Override
  public void stop() {
    if (scheduler != null && !scheduler.isShutdown()) {
      scheduler.shutdownNow();
      scheduler = null;
    }

    i = 0;
    System.out.println("FakeDataSource stopped");
  }

  /**
   * Sets the data listener for this data source.
   * This listener will be notified whenever new data, such as a {@link ShipDto},
   * is generated by the fake data source. The assigned listener must implement
   * the {@link DataListener} interface, with its {@code onDataReceived} method
   * being invoked upon data updates.
   *
   * @param listener The {@link DataListener} instance to receive updated ship data.
   */
  @Override
  public void setDataListener(DataListener listener) {
    this.listener = listener;
    System.out.println("FakeDataSource sending data");
  }
}
