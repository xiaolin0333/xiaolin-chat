package pers.xiaolin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xiaolin.dto.UserLoginDTO;
import pers.xiaolin.result.Result;
import pers.xiaolin.service.UserService;
import pers.xiaolin.vo.UserLoginVO;

import javax.annotation.Resource;

/**
 * @author xiaolin03
 * @date 2025/5/28
 */
@Schema(description = "用户操作相关接口")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("/login")
    @Operation(description = "登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        return Result.success(userService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword()));
    }
}
