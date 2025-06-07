package pers.xiaolin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xiaolin03
 * @date 2025/5/31
 */
@Data
public class UserMessageVO {
    @Schema(description = "消息内容")
    private String content;
    @Schema(description = "发送人id")
    private String senderId;
    @Schema(description = "收件人id")
    private String receiverId;
    @Schema(description = "发送的时间")
    private LocalDateTime sendTime;
}
