package no.ntnu.idata2900.project.backend.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.ntnu.idata2900.project.backend.models.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ConfigurationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    @Test
    void testCreateConfiguration() throws Exception {
        Configuration config = new Configuration("REG123", "VesselName", "Diesel", true);

        mockMvc.perform(post("/api/config")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(config)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetConfiguration() throws Exception {
        Configuration config = new Configuration("REG123", "VesselName", "Diesel", true);
        mockMvc.perform(post("/api/config")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(config)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/config/REG123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.registrationMark").value("REG123"))
                .andExpect(jsonPath("$.name").value("VesselName"))
                .andExpect(jsonPath("$.fuelType").value("Diesel"))
                .andExpect(jsonPath("$.level1").value(true));
    }

    @Test
    void testUpdateConfiguration() throws Exception {
        Configuration config = new Configuration("REG123", "VesselName", "Diesel", true);
        mockMvc.perform(post("/api/config")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(config)))
                .andExpect(status().isOk());

        config.setName("UpdatedVesselName");
        mockMvc.perform(put("/api/config/REG123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(config)))
                .andExpect(status().isOk()); // Expect 200 OK
    }

    @Test
    void testDeleteConfiguration() throws Exception {
        Configuration config = new Configuration("REG123", "VesselName", "Diesel", true);
        mockMvc.perform(post("/api/config")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(config)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/config/REG123"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/config/REG123"))
                .andExpect(status().isNotFound());
    }
}
