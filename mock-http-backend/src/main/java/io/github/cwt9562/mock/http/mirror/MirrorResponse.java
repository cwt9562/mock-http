package io.github.cwt9562.mock.http.mirror;

import java.util.Map;

import com.google.common.collect.Maps;
import lombok.Data;

@Data
public class MirrorResponse {

    private String uri;
    private Map<String, String> params = Maps.newHashMap();
    private Map<String, String> headers = Maps.newHashMap();
    private String body;
    private Long timestamp;

}
