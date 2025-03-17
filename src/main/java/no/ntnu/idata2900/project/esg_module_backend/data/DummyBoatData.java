package no.ntnu.idata2900.project.esg_module_backend.data;

import no.ntnu.idata2900.project.esg_module_backend.models.BoatData;

public class DummyBoatData {
    private final BoatData[] dummyBoatData = {
            new BoatData(1, "Boat 1", 63.429, 10.393, 0.0, 0.0, 5.0, "2025-02-13T12:00:00"),
            new BoatData(1, "Boat 2", 63.430, 10.394, 10.0, 5.0, 10.0, "2025-02-13T12:01:00"),
            new BoatData(1, "Boat 3", 63.431, 10.395, 20.0, 10.0, 15.0, "2025-02-13T12:02:00"),
            new BoatData(1, "Boat 4", 63.432, 10.396, 30.0, 15.0, 20.0, "2025-02-13T12:03:00"),
            new BoatData(1, "Boat 5", 63.433, 10.397, 40.0, 20.0, 25.0, "2025-02-13T12:04:00")
    };

    public BoatData getDummyBoatData(int i) {
        return this.dummyBoatData[i];
    }
}
