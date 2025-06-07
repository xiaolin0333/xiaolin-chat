package pers.xiaolin.service;


import pers.xiaolin.vo.UserLoginVO;

public interface UserService {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    UserLoginVO login(String username, String password);

}
