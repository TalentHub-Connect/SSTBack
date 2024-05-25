package talentsoft.sstback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import talentsoft.sstback.model.Capacitation;
import talentsoft.sstback.payload.request.CapacitationRequest;
import talentsoft.sstback.payload.request.CapacitationStatusUpdateRequest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@WebAppConfiguration
class CapacitationControllerTest {

    private final static String CAPACITATION_CONTROLLER_PATH = "/api/capacitations";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getAllCapacitations() throws Exception {
        mockMvc.perform(get(CAPACITATION_CONTROLLER_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAllCapacitationsByCompany() throws Exception {
        int companyId = 1;
        mockMvc.perform(get(CAPACITATION_CONTROLLER_PATH + "/company/{companyId}", companyId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getCapacitationById() throws Exception {
        int capacitationId = 1;
        mockMvc.perform(get(CAPACITATION_CONTROLLER_PATH + "/{id}", capacitationId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createCapacitation() throws Exception {
        CapacitationRequest request = new CapacitationRequest();
        request.setCapacitationDate("2021-12-12");
        request.setCompanyid(1);
        request.setPlace("Place of the new capacitation");
        request.setStatus("Status of the new capacitation");
        request.setTypeCapacitationId(1);
        request.setDescription("Description of the new capacitation");

        mockMvc.perform(post(CAPACITATION_CONTROLLER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void updateCapacitationStatus() throws Exception {
        int capacitationId = 1;
        CapacitationStatusUpdateRequest request = new CapacitationStatusUpdateRequest();
        request.setStatus("Updated Status");

        mockMvc.perform(put(CAPACITATION_CONTROLLER_PATH + "/{id}", capacitationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void updateCapacitationDetails() throws Exception {
        int capacitationId = 1;
        Capacitation capacitation = new Capacitation();
        capacitation.setStatus("Updated Status");
        capacitation.setDescription("Updated Description");

        mockMvc.perform(put(CAPACITATION_CONTROLLER_PATH + "/{id}/details", capacitationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(capacitation)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void FailedUpdateCapacitationDetails() throws Exception {
        int capacitationId = 3;
        Capacitation capacitation = new Capacitation();
        capacitation.setStatus("Updated Status");
        capacitation.setDescription("Updated Description");

        mockMvc.perform(put(CAPACITATION_CONTROLLER_PATH + "/{id}/details", capacitationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(capacitation)))
                .andExpect(status().isNotFound());
    }
}