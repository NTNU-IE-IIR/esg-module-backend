package no.ntnu.idata2900.project.esg_module_backend.sources;

import no.ntnu.idata2900.project.esg_module_backend.dtos.BoatDataDto;

public interface DataListener {
    void onDataReceived(BoatDataDto data);
}