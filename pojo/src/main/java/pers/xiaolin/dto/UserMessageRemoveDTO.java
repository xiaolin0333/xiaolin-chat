package pers.xiaolin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author xiaolin03
 * @date 2025/6/1
 */
@Data
@Schema(description = "撤回消息请求体")
public class UserMessageRemoveDTO {
    @Schema(description = "要撤回的消息id", required = true)
    @NotNull(message = "消息id不能为空")
    private String id;
}
