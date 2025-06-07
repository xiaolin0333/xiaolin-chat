package pers.xiaolin.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author xiaolin03
 * @date 2025/5/30
 */
public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 1. 获取HttpSession对象
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        // 2. 将HttpSession对象保存起来
        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}