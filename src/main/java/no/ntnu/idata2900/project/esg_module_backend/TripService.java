package no.ntnu.idata2900.project.esg_module_backend;

import no.ntnu.idata2900.project.esg_module_backend.models.BoatData;
import no.ntnu.idata2900.project.esg_module_backend.models.Trip;
import no.ntnu.idata2900.project.esg_module_backend.models.TripLog;
import no.ntnu.idata2900.project.esg_module_backend.sources.DataListener;
import no.ntnu.idata2900.project.esg_module_backend.sources.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService implements DataListener {
    private Trip currentTrip;
    private final DataSource dataSource;
    private BoatDataHandler boatDataHandler;

    @Autowired
    TripService(DataSource dataSource, BoatDataHandler boatDataHandler) {
        this.dataSource = dataSource;
        this.boatDataHandler = boatDataHandler;
    }

    public void startTrip() {
        currentTrip = new Trip();
        currentTrip.start();
        dataSource.setDataListener(this);
        dataSource.start();
        // Start trip
        System.out.println("Trip started");
    }

    public void stopTrip() {
        dataSource.stop();
        currentTrip.end();
        dataSource.stop();
        // Stop trip
    }

    public Trip getCurrentTrip() {
        return currentTrip;
    }


    @Override
    public void onDataReceived(BoatData data) {
        System.out.println("Data received: " + data);
        if (currentTrip != null) {
            currentTrip.addBoatData(data);
        }
        if (boatDataHandler.isConnected())
            boatDataHandler.sendBoatData(data);
    }
}
