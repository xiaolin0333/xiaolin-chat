package pers.xiaolin.mqmessage;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author xiaolin03
 * @date 2025/6/3
 */
@Data
@Accessors(chain = true)
public class SendMessage {
    public static final String QUEUE = "SEND_MESSAGE_QUEUE";

    /*消息id*/
    private String messageId;
    /*发送人id*/
    private String senderId;
    /*收件人id*/
    private String receiverId;
    /*发送事件*/
    private LocalDateTime sendTime;
    /*消息内容*/
    private String content;
    private boolean status = false;
}
