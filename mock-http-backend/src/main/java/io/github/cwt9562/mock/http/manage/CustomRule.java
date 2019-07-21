package io.github.cwt9562.mock.http.manage;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@TableName(value = "CUSTOM_RULE")
@Data
public class CustomRule {

    @TableField("ID")
    private String id;
    @TableField("REQ_URI")
    private String reqUri;
    @TableField("RESP_STATUS_CODE")
    private Integer respStatusCode;
    @TableField("RESP_CONTENT_TYPE")
    private String respContentType;
    @TableField("RESP_BODY")
    private String respBody;
    @TableField("CREATE_AT")
    private String createAt;
    @TableField("UPDATE_AT")
    private String updateAt;
}
