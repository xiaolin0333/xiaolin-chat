package pers.xiaolin.mqmessage;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xiaolin03
 * @date 2025/6/3
 */
@Data
@Component
@Slf4j
public class SendMessageProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;
    public void sendMessage(SendMessage message) {
        try {
            rabbitTemplate.convertAndSend(SendMessage.QUEUE, JSONUtil.toJsonStr(message)); // 往消息队列里发消息
        }catch (Exception e) {
            log.error("[SendMessageProducer][sendMessage]发送消息失败，sendMessage = {}", JSONUtil.toJsonStr(message), e);
        }
    }
}
