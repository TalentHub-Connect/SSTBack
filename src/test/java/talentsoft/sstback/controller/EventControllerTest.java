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
import talentsoft.sstback.payload.request.EventRequest;
import talentsoft.sstback.payload.request.EventUpdateRequest;
import talentsoft.sstback.payload.request.EventUpdateStatusRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@WebAppConfiguration
class EventControllerTest {

    private final static String EVENT_CONTROLLER_PATH = "/api/events";

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
    void getAllEvents() throws Exception {
        mockMvc.perform(get(EVENT_CONTROLLER_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetEventById() throws Exception {
        int eventId = 102;
        mockMvc.perform(get(EVENT_CONTROLLER_PATH + "/{id}", eventId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void FailGetEventById() throws Exception {
        int eventId = 1;
        mockMvc.perform(get(EVENT_CONTROLLER_PATH + "/{id}", eventId))
                .andExpect(status().isNotFound());
    }

    @Test
    void GetEventsByCompany() throws Exception {
        int companyId = 1;
        mockMvc.perform(get(EVENT_CONTROLLER_PATH + "/company/{companyId}", companyId))
                .andExpect(status().isOk());
    }

    @Test
    void FailGetEventsByCompany() throws Exception {
        int companyId = 2;
        mockMvc.perform(get(EVENT_CONTROLLER_PATH + "/company/{companyId}", companyId))
                .andExpect(status().isNotFound());
    }

    @Test
    void getEventsByCompany() throws Exception {
        int companyId = 1;
        mockMvc.perform(get(EVENT_CONTROLLER_PATH + "/company/{companyId}", companyId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void addEvent() throws Exception {
        EventRequest request = new EventRequest();
        request.setCompanyid(1);
        request.setTypeeventid(1);
        request.setStatus("Activo");
        request.setDateEvent("2021-10-10");
        request.setDescription("Description of the new event");

        mockMvc.perform(post(EVENT_CONTROLLER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void UpdateEventStatus() throws Exception {
        int eventId = 1;
        EventUpdateStatusRequest request = new EventUpdateStatusRequest();
        request.setStatus("Updated Status");
        mockMvc.perform(put(EVENT_CONTROLLER_PATH + "/{id}/status", eventId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void FailUpdateEventDetails() throws Exception {
        int eventId = 1;
        EventUpdateRequest request = new EventUpdateRequest();
        request.setStatus("Updated Status");
        request.setDescription("Updated Description");

        mockMvc.perform(put(EVENT_CONTROLLER_PATH + "/{id}/details", eventId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }
}