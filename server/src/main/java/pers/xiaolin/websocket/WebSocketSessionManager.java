package pers.xiaolin.websocket;

import javax.websocket.Session;
import java.util.Collection;

/**
 * WebSocket 会话管理器接口
 */
public interface WebSocketSessionManager {
    /**
     * 添加 Session
     *
     * @param session Session
     */
    void addSession(Session session);

    /**
     * 移除 Session
     *
     * @param session Session
     */
    void removeSession(Session session);

    /**
     * 获得指定编号的 Session
     *
     * @param id Session 编号
     * @return Session
     */
    Session getSession(String id);

    /**
     * 获得指定用户类型的 Session 列表
     *
     * @return Session 列表
     */
    Collection<Session> getSessionList();
}