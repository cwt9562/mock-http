package io.github.cwt9562.mock.http.mirror;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.extern.java.Log;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class MirrorController {

    @Autowired
    private Gson gson;

    @RequestMapping(value = "/mirror/**")
    public ResponseEntity<String> mirror(HttpServletRequest request) {
        MirrorResponse response = buildResponse(request);
        String responseBody = gson.toJson(response);
        return ResponseEntity.ok()
            .body(responseBody);
    }

    protected MirrorResponse buildResponse(HttpServletRequest request) {
        return new MirrorResponse()
            .setUri(
                StringUtils.removeStartIgnoreCase(request.getRequestURI(),"/mirror")
            )
            .setParams(
                Optional.ofNullable(request.getParameterMap())
                    .map(stringMap -> {
                        Map<String, String> map = Maps.newHashMap();
                        stringMap.forEach((s, strings) -> map.put(s, strings[0]));
                        return map;
                    })
                    .orElse(Collections.emptyMap())
            )
            .setHeaders(
                Optional.ofNullable(request.getHeaderNames())
                    .map(enumerations -> {
                        Map<String, String> map = Maps.newHashMap();
                        while (enumerations.hasMoreElements()) {
                            String name = enumerations.nextElement();
                            map.put(name, request.getHeader(name));
                        }
                        return map;
                    })
                    .orElse(Collections.emptyMap())
            )
            .setBody(
                ((Supplier<String>) () -> {
                    try {
                        return IOUtils.toString(request.getInputStream(), Charsets.UTF_8);
                    } catch (IOException ignore) {
                        return null;
                    }
                }).get()
            )
            .setTimestamp(System.currentTimeMillis());
    }
}
