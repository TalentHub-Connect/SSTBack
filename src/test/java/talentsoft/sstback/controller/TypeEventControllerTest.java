package talentsoft.sstback.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
class TypeEventControllerTest {

    private final static String TYPE_EVENT_CONTROLLER_PATH = "/api/type/events";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void getAllTypeEvents() throws Exception {
        mockMvc.perform(get(TYPE_EVENT_CONTROLLER_PATH))
                .andExpect(status().isOk());
    }

    @Test
    void getTypeEventById() throws Exception {
        int id = 1;
            mockMvc.perform(get(TYPE_EVENT_CONTROLLER_PATH + "/" + id))
                    .andExpect(status().isOk());
    }
}