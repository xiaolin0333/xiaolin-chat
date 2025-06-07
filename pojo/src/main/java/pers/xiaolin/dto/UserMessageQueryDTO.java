package pers.xiaolin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author xiaolin03
 * @date 2025/6/1
 */
@Schema(description = "聊天记录内容查询条件")
@Data
public class UserMessageQueryDTO extends PageParam {
    @Schema(description = "接收id", required = true)
    @NotNull(message = "收件人id不能为空")
    private String receiverId;
}
