package io.github.cwt9562.mock.http.custom;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class CustomController {

    @Autowired
    private CustomRuleService customRuleService;

    @RequestMapping(value = "/custom/**")
    public ResponseEntity<String> custom(HttpServletRequest request) {
        String method = request.getMethod();
        String uri = StringUtils.removeStartIgnoreCase(request.getRequestURI(), "/custom");
        CustomRule customRule = customRuleService.getOne(
            new QueryWrapper<CustomRule>()
                .eq("REQ_METHOD", StringUtils.upperCase(method))
                .eq("REQ_URI", uri)
        );
        Integer respStatusCode = customRule.getRespStatusCode();
        String respContentType = customRule.getRespContentType();
        String respBody = customRule.getRespBody();

        ResponseEntity.BodyBuilder builder = ResponseEntity.status(respStatusCode);
        if (StringUtils.isNotEmpty(respContentType)) {
            builder.contentType(MediaType.valueOf(respContentType));
        }
        if (StringUtils.isNotEmpty(respBody)) {
            return builder.body(respBody);
        }
        return builder.build();
    }
}
