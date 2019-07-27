package io.github.cwt9562.mock.http.custom;

import io.github.cwt9562.mock.http.Application;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("custom")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomControllerTest {

    @Autowired
    protected MockMvc mockMvc;

//    @Autowired
//    private Gson gson;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/custom/sample-200")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPost() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/custom/sample-400")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is4xxClientError())
            .andExpect(MockMvcResultMatchers.jsonPath("$.x", Matchers.is("y")));
    }

}