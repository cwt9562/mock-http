package io.github.cwt9562.mock.http.manage;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import io.github.cwt9562.mock.http.Application;
import org.apache.commons.collections4.CollectionUtils;
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
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("mirror")
public class ManageControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected CustomRuleMapper mapper;
    @Autowired
    private Gson gson;

    @Test
    public void list() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/manage/custom-rules")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void filter() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/manage/custom-rules")
                .param("uri", "200")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void create() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/manage/custom-rules")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(
                    gson.toJson(
                        new CustomRule()
                            .setReqUri("/test-create")
                            .setRespStatusCode(200)
                    )
                )
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk());
        List<CustomRule> customRules = mapper.selectList(
            new QueryWrapper<CustomRule>()
                .eq("req_uri", "/test-create")
        );
        Assert.notEmpty(customRules);
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.put("/manage/custom-rules")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(
                    gson.toJson(
                        new CustomRule()
                            .setId("0c9046c3-d8cc-4afb-9a12-0799ea413712")
                            .setReqUri("/test-update-after")
                            .setRespStatusCode(300)
                    )
                )
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk());
        List<CustomRule> customRules = mapper.selectList(
            new QueryWrapper<CustomRule>()
                .eq("req_uri", "/test-update")
        );
        Assert.isTrue(CollectionUtils.isEmpty(customRules));
    }
}