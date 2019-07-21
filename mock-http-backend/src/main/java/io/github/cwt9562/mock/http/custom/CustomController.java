package io.github.cwt9562.mock.http.custom;

import javax.servlet.http.HttpServletRequest;

import io.github.cwt9562.mock.http.mirror.MirrorResponse;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class CustomController {


    @RequestMapping(value = "/custom/**")
    public ResponseEntity<String> custom(HttpServletRequest request) {
        return ResponseEntity.ok().build();
    }
}
