package talentsoft.sstback.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@WebAppConfiguration
class TypeCapacitationControllerTest {

    private final static String TYPE_CAPACITATION_CONTROLLER_PATH = "/api/type/capacitations";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getAllTypeCapacitations() throws Exception {
        mockMvc.perform(get(TYPE_CAPACITATION_CONTROLLER_PATH))
                .andExpect(status().isOk());
    }

    @Test
    void getTypeCapacitationById() throws Exception {
        int id = 3;
        mockMvc.perform(get(TYPE_CAPACITATION_CONTROLLER_PATH + "/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void getTypeCapacitationByIdNotFound() throws Exception {
        int id = 99;
        mockMvc.perform(get(TYPE_CAPACITATION_CONTROLLER_PATH + "/{id}", id))
                .andExpect(status().isNotFound());
    }
}