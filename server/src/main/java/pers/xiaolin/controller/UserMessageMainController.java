package pers.xiaolin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xiaolin.result.Result;
import pers.xiaolin.service.UserMessageMainService;
import pers.xiaolin.vo.UserMessageMainVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaolin03
 * @date 2025/5/30
 */
@Tag(name = "聊天记录主表数据")
@RestController
@RequestMapping("/user-message-main")
public class UserMessageMainController {
    @Resource
    private UserMessageMainService userMessageMainService;

    @PostMapping("/list")
    @Operation(summary = "查询聊天记录列表")
    public Result<List<UserMessageMainVO>> getMessageMainList() {
        return Result.success(userMessageMainService.getMessageMainList());
    }
}
