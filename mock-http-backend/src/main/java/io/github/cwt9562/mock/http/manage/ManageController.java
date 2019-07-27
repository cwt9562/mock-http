package io.github.cwt9562.mock.http.manage;

import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.cwt9562.mock.http.utils.Dates;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage")
@Log
public class ManageController {

    @Autowired
    private CustomRuleService customRuleService;

    @GetMapping(value = "/custom-rules", produces = "application/json")
    public ResponseEntity<IPage<CustomRule>> list(@RequestParam(required = false, defaultValue = "1") Integer current,
                                                  @RequestParam(required = false, defaultValue = "10") Integer size,
                                                  @RequestParam(required = false) String uri) {
        return ResponseEntity.ok(
            customRuleService.page(
                new Page<>(current, size),
                new QueryWrapper<CustomRule>()
                    .like(uri != null, "req_uri", uri)
            )
        );
    }

    @GetMapping(value = "/custom-rules/{id}", produces = "application/json")
    public ResponseEntity<CustomRule> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(
            customRuleService.getById(id)
        );
    }

    @PostMapping(value = "/custom-rules", produces = "application/json")
    public ResponseEntity<CustomRule> create(@RequestBody CustomRule customRule) {
        customRule.setId(UUID.randomUUID().toString().toLowerCase());
        customRule.setCreateAt(Dates.format(Dates.now()));
        customRule.setUpdateAt(Dates.format(Dates.now()));
        customRuleService.save(customRule);
        return ResponseEntity.ok(customRule);
    }

    @PutMapping(value = "/custom-rules/{id}", produces = "application/json")
    public ResponseEntity<CustomRule> update(@PathVariable("id") String id,
                                             @RequestBody CustomRule customRule) {
        CustomRule before = customRuleService.getById(id);
        before.setReqUri(customRule.getReqUri());
        before.setRespStatusCode(customRule.getRespStatusCode());
        before.setRespContentType(customRule.getRespContentType());
        before.setRespBody(customRule.getRespBody());
        before.setUpdateAt(Dates.format(Dates.now()));
        customRuleService.updateById(before);
        return ResponseEntity.ok(before);
    }

    @DeleteMapping(value = "/custom-rules/{id}", produces = "application/json")
    public void delete(@PathVariable("id") String id) {
        customRuleService.removeById(id);
    }

}
