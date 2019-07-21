package io.github.cwt9562.mock.http.mirror;

import io.github.cwt9562.mock.http.Application;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("mirror")
public class MirrorControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void mirror() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/mirror/test/abc")
                .param("a", "1")
                .header("b", "2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"c\":\"3\"}")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.uri", Matchers.is("/test/abc")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.params.a", Matchers.is("1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.headers.b", Matchers.is("2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.body", Matchers.is("{\"c\":\"3\"}")));
    }
}