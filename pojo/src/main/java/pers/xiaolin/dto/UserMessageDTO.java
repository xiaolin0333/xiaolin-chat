package pers.xiaolin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 聊天记录内容对象
 * @author xiaolin03
 * @date 2025/5/30
 */
@Data
public class UserMessageDTO {
    @Schema(description = "消息内容")
    private String content;
    @Schema(description = "收件人id")
    private String receiverId;
}
