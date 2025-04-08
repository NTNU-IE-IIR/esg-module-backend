package no.ntnu.idata2900.project.esg_module_backend.sources;

import no.ntnu.idata2900.project.esg_module_backend.dtos.ShipDto;

public interface DataListener {
  void onDataReceived(ShipDto ship);
}
