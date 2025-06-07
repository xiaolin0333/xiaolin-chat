package pers.xiaolin.websocket;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.xiaolin.context.BaseContext;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * WebSocket 会话管理器
 */
@Slf4j
@Component
public class WebSocketSessionManagerImpl implements WebSocketSessionManager {
    /**
     * userId -> WebSocket会话
     */
    private static final Map<String, Session> idSessions = new ConcurrentHashMap<>();
    @Override
    public void addSession(Session session) {
        idSessions.put((String) session.getUserProperties().get("userId"), session);
    }

    @Override
    public void removeSession(Session session) {
        idSessions.remove(BaseContext.getCurrentId());
    }

    @Override
    public Session getSession(String id) {
        return idSessions.get(id);
    }

    @Override
    public Collection<Session> getSessionList() {
        return new ArrayList<>(idSessions.values());
    }
}