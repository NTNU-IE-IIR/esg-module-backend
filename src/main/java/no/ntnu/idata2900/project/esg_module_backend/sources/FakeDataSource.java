package no.ntnu.idata2900.project.esg_module_backend.sources;

import java.util.Timer;
import java.util.TimerTask;
import no.ntnu.idata2900.project.esg_module_backend.data.DummyBoatData;
import no.ntnu.idata2900.project.esg_module_backend.models.BoatData;
import org.springframework.stereotype.Component;

@Component
public class FakeDataSource implements DataSource {
    private Timer timer;
    private DataListener listener;
    private int i = 0;

    @Override
    public void start() {
        DummyBoatData dummyBoatData = new DummyBoatData();
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (listener != null) {
                    BoatData data = dummyBoatData.getDummyBoatData(i);
                    listener.onDataReceived(data);
                }
                i = (i + 1) % 5;
            }
        };

        timer.schedule(task, 0, 15000);
        System.out.println("FakeDataSource started");
    }

    @Override
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        System.out.println("FakeDataSource stopped");
    }

    @Override
    public void setDataListener(DataListener listener) {
        this.listener = listener;
        System.out.println("FakeDataSource sending data");
    }
}
