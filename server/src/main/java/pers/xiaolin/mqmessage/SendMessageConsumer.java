package pers.xiaolin.mqmessage;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import pers.xiaolin.websocket.WebSocketSessionManager;

import javax.annotation.Resource;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author xiaolin03
 * @date 2025/6/3
 */
@Component
@RabbitListener(queues = SendMessage.QUEUE) // 消费者监听的队列
public class SendMessageConsumer {
    @Resource
    private WebSocketSessionManager sessionManager;

    @RabbitHandler
    public void onMessage(String messageJson, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        SendMessage message = JSONUtil.toBean(messageJson, SendMessage.class);
        try {
            if(message.isStatus()) { // 默认是false
                channel.basicAck(tag, false); // 确认当前消息
                return;
            }
            // 处理消息的具体逻辑
            if(processed(message)) {
                channel.basicAck(tag, false);
            } else {
                channel.basicAck(tag, false); // 不在线，将消息标记为已被处理（此时消息不再入队）
            }
        }catch (Exception e){
            System.out.println("faild to process message" + message);
            // 拒绝消息并重新入队
            channel.basicNack(tag, false, true);
        }
    }

    private boolean processed(SendMessage message) throws IOException {
        Session session = sessionManager.getSession(message.getReceiverId());

        if(BeanUtil.isEmpty(session)) {
            return false;
        }
        HashMap<String, String> mp = new HashMap<>();
        mp.put("time", String.valueOf(message.getSendTime()));
        mp.put("senderId", String.valueOf(message.getSenderId()));
        mp.put("messageId", String.valueOf(message.getMessageId()));
        mp.put("content", message.getContent());
        // 修复：只序列化一次
        String jsonStr = JSONUtil.toJsonStr(mp);

        session.getBasicRemote().sendText(jsonStr);
        return true;
    }
}
