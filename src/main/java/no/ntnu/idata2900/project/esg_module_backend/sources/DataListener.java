package no.ntnu.idata2900.project.esg_module_backend.sources;

import no.ntnu.idata2900.project.esg_module_backend.models.BoatData;

public interface DataListener {
    void onDataReceived(BoatData data);
}