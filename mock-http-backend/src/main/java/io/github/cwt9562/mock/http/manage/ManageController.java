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

    @GetMapping("/custom-rules")
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

    @PostMapping("/custom-rules")
    public CustomRule create(@RequestBody CustomRule customRule) {
        customRule.setId(UUID.randomUUID().toString().toLowerCase());
        customRule.setCreateAt(Dates.format(Dates.now()));
        customRule.setUpdateAt(Dates.format(Dates.now()));
        customRuleService.save(customRule);
        return customRule;
    }

    @PutMapping("/custom-rules")
    public CustomRule update(@RequestBody CustomRule customRule) {
        String id = customRule.getId();
        CustomRule before = customRuleService.getById(id);
        before.setReqUri(customRule.getReqUri());
        before.setRespStatusCode(customRule.getRespStatusCode());
        before.setRespContentType(customRule.getRespContentType());
        before.setRespBody(customRule.getRespBody());
        customRule.setUpdateAt(Dates.format(Dates.now()));
        customRuleService.updateById(before);
        return before;
    }

    @DeleteMapping("/custom-rules")
    public void delete(@RequestBody CustomRule customRule) {
        customRuleService.removeById(customRule.getId());
    }
}
