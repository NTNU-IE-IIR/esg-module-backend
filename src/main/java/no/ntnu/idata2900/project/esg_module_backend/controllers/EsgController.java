package no.ntnu.idata2900.project.esg_module_backend.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import java.util.Optional;
import no.ntnu.idata2900.project.esg_module_backend.dtos.EsgDto;
import no.ntnu.idata2900.project.esg_module_backend.services.EsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling Environmental, Social, and Governance (ESG) data requests.
 * This controller provides endpoints for retrieving ESG data for vessels based on registration mark
 * and time period.
 */
@RestController
@RequestMapping("api/esg")
@Tag(name = "Esg Controller", description = "API for ESG data")
public class EsgController {
    /**
     * Service for handling ESG data operations.
     */
    private final EsgService esgService;

    /**
     * Logger for this controller.
     */
    private final Logger logger = LoggerFactory.getLogger(EsgController.class);

    /**
     * Constructs a new EsgController with the specified EsgService.
     *
     * @param esgService The service for handling ESG data operations
     */
    @Autowired
    public EsgController(EsgService esgService) {
        this.esgService = esgService;
    }

    /**
     * Retrieves ESG data for a vessel identified by its registration mark.
     * 
     * @param regMark The registration mark of the vessel
     * @return ResponseEntity containing the ESG data if found, or a 404 Not Found response if no data exists
     */
    @GetMapping("/vessel/{regMark}")
    public ResponseEntity<EsgDto> esgFromVessel(@PathVariable String regMark) {
        ResponseEntity<EsgDto> response;

        Optional<EsgDto> esgDto = esgService.esgFromVessel(regMark);

        if (esgDto.isPresent()) {
            logger.info("ESG data for vessel with registration mark {} was found", regMark);
            response = ResponseEntity.ok().body(esgDto.get());
        } else {
            logger.error("ESG data for vessel with registration mark {} was not found", regMark);
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    /**
     * Retrieves ESG data for a vessel identified by its registration mark within a specific time period.
     * 
     * @param regMark The registration mark of the vessel
     * @param requestBody A map containing the start and end timestamps:
     *                    - "startTs": The start timestamp in milliseconds since epoch
     *                    - "endTs": The end timestamp in milliseconds since epoch
     * @return ResponseEntity containing the ESG data if found, or a 404 Not Found response if no data exists
     */
    @GetMapping("/vesselTime/{regMark}")
    public ResponseEntity<EsgDto> esgFromVesselTime(@PathVariable String regMark, @RequestBody
    Map<String, String> requestBody) {
        ResponseEntity<EsgDto> response;

        long startTs = Long.parseLong(requestBody.get("startTs"));
        long endTs = Long.parseLong(requestBody.get("endTs"));

        Optional<EsgDto> esgDto = esgService.esgFromVesselTime(regMark, startTs, endTs);

        if (esgDto.isPresent()) {
            logger.info("ESG time period data for vessel with registration mark {} was found", regMark);
            response = ResponseEntity.ok().body(esgDto.get());
        } else {
            logger.error("ESG time period data for vessel with registration mark {} was not found", regMark);
            response = ResponseEntity.notFound().build();
        }

        return response;
    }
}
