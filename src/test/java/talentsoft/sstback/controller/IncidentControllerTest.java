package talentsoft.sstback.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import talentsoft.sstback.model.Incident;
import talentsoft.sstback.payload.request.IncidentRequest;
import talentsoft.sstback.payload.request.UpdateIncidentRequest;
import talentsoft.sstback.payload.request.updateIncidentStatusRequest;
import talentsoft.sstback.service.impl.IncidentService;

@SpringBootTest
@WebAppConfiguration
public class IncidentControllerTest {

    private final static String INCIDENT_CONTROLLER_PATH = "/api/incidents";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Mock
    private IncidentService incidentService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testGetAllIncidents() throws Exception {
        mockMvc.perform(get(INCIDENT_CONTROLLER_PATH))
                .andExpect(status().isOk());
    }

    @Test
    void testGetIncidentById() throws Exception {
        mockMvc.perform(get(INCIDENT_CONTROLLER_PATH + "/{id}", 3))
                .andExpect(status().isOk());
    }

    @Test
    void testFailGetIncidentById() throws Exception {
        mockMvc.perform(get(INCIDENT_CONTROLLER_PATH + "/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetIncidentByIdNotFound() throws Exception {
        mockMvc.perform(get(INCIDENT_CONTROLLER_PATH + "/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetIncidentsByCompany() throws Exception {
        mockMvc.perform(get(INCIDENT_CONTROLLER_PATH + "/company/{companyId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void testAddIncident() throws Exception {
        IncidentRequest request = new IncidentRequest("Incident 1", "2021-06-01", "Open", 1, 1, 1);
        Incident incident = new Incident(1, "Incident 1", "2021-06-01", "Open", 1, 1, 1);
        when(incidentService.saveIncident(any(IncidentRequest.class))).thenReturn(incident);

        mockMvc.perform(post(INCIDENT_CONTROLLER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateIncident() throws Exception {
        UpdateIncidentRequest request = new UpdateIncidentRequest("Updated Incident", "OPEN");

        mockMvc.perform(put(INCIDENT_CONTROLLER_PATH + "/{id}", 4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateIncidentStatus() throws Exception {
        updateIncidentStatusRequest request = new updateIncidentStatusRequest("Closed");

        mockMvc.perform(put(INCIDENT_CONTROLLER_PATH + "/status/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteIncident() throws Exception {
        mockMvc.perform(delete(INCIDENT_CONTROLLER_PATH + "/{id}", 11))
                .andExpect(status().isOk());
    }

    @Test
    void testFailDeleteIncident() throws Exception {
        mockMvc.perform(delete(INCIDENT_CONTROLLER_PATH + "/{id}", 999))
                .andExpect(status().isNotFound());
    }
}