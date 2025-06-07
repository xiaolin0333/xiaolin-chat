package pers.xiaolin.service.impl;


import cn.hutool.core.lang.Assert;
import org.springframework.util.DigestUtils;
import pers.xiaolin.constant.JwtClaimsConstant;
import pers.xiaolin.constant.MessageConstant;
import pers.xiaolin.exception.ServiceException;
import pers.xiaolin.vo.UserLoginVO;
import pers.xiaolin.entity.User;
import pers.xiaolin.mapper.UserMapper;
import pers.xiaolin.properties.JwtProperties;
import pers.xiaolin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xiaolin.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtProperties jwtProperties;
    @Override
    public UserLoginVO login(String username, String password) {
        User user = userMapper.getByUsername(username);
        Assert.isTrue(user != null, ()-> new ServiceException(1000, MessageConstant.ACCOUNT_NOT_FOUND)); // 用户名是否存在
        password = DigestUtils.md5DigestAsHex(password.getBytes()); // 加密
        Assert.isTrue(password.equals(user.getPassword()), () -> new ServiceException(1000, MessageConstant.PASSWORD_ERROR)); // 账号是否正确
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);
        return new UserLoginVO().setId(user.getId()).setUsername(username).setToken(token);
    }
}
