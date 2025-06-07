package pers.xiaolin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xiaolin03
 * @date 2025/5/30
 */
@Data
public class UserMessageMainVO {
    @Schema(description = "发件人id")
    private String senderId;
    @Schema(description = "发件人名")
    private String senderName;
    @Schema(description = "收件人id")
    private String receiverId;
    @Schema(description = "收件人名")
    private String receiverName;
    @Schema(description = "最后一次发送消息的时间")
    private String endSendTime;
    @Schema(description = "未读数")
    private Integer messageCount;
}
