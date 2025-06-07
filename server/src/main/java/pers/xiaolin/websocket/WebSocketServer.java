package pers.xiaolin.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket服务器
 */
@Slf4j
@Component
@ServerEndpoint("/ws/chat/{userId}")
public class WebSocketServer {

    private WebSocketSessionManager sessionManager = new WebSocketSessionManagerImpl();

    /**
     * 连接建立时调用
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        try {
            log.info("[WebSocket] 新的连接建立，userId：{}，sessionId：{}", userId, session.getId());
            // 添加session到map集合
            session.getUserProperties().put("userId", userId);
            sessionManager.addSession(session);
            log.info("[WebSocket] session已添加到map集合");
        } catch (Exception e) {
            log.error("[WebSocket] 处理连接建立失败", e);
            try {
                session.close();
            } catch (IOException ex) {
                log.error("[WebSocket] 关闭连接失败", ex);
            }
        }
    }

    /**
     * 收到客户端消息时调用
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            String userId = (String) session.getUserProperties().get("userId");
            log.info("[WebSocket] 收到消息，userId：{}，message：{}", userId, message);
            // 这里可以添加消息处理逻辑
        } catch (Exception e) {
            log.error("[WebSocket] 处理消息失败", e);
        }
    }

    /**
     * 连接关闭时调用
     */
    @OnClose
    public void onClose(Session session) {
        try {
            String userId = (String) session.getUserProperties().get("userId");
            log.info("[WebSocket] 连接关闭，userId：{}，sessionId：{}", userId, session.getId());
            // 从map集合中移除session
            sessionManager.removeSession(session);
        } catch (Exception e) {
            log.error("[WebSocket] 处理连接关闭失败", e);
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        try {
            String userId = (String) session.getUserProperties().get("userId");
            log.error("[WebSocket] 发生错误，userId：{}，sessionId：{}，error：{}", userId, session.getId(), error.getMessage());
            // 从map集合中移除session
            sessionManager.removeSession(session);
            // 尝试关闭连接
            try {
                session.close();
            } catch (IOException e) {
                log.error("[WebSocket] 关闭错误连接失败", e);
            }
        } catch (Exception e) {
            log.error("[WebSocket] 处理错误失败", e);
        }
    }
} 