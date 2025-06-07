package pers.xiaolin.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import pers.xiaolin.constant.JwtClaimsConstant;
import pers.xiaolin.context.BaseContext;
import pers.xiaolin.properties.JwtProperties;
import pers.xiaolin.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaolin03
 * @date 2024/11/30
 */
@Component
public class JwtTokenUserInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断当前拦截到的是Controller还是其他资源
        if(!(handler instanceof HandlerMethod)){
            // 拦截到的不是动态方法
            return true;// 直接放行
        }
        try {
            // 1.从请求头中获取token
            String token = request.getHeader(jwtProperties.getUserTokenName());
            // 2.校验token
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);

            String userId = claims.get(JwtClaimsConstant.USER_ID).toString();
            // 把userId放入TreadLocal里
            BaseContext.setCurrentId(userId);
            // 3.放行
            return true;
        }catch (Exception e){
            // 不通过，相应401
            response.setStatus(401);
            return false;
        }
    }
}
