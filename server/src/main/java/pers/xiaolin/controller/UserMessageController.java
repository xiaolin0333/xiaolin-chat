package pers.xiaolin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xiaolin.dto.UserMessageQueryDTO;
import pers.xiaolin.dto.UserMessageRemoveDTO;
import pers.xiaolin.result.PageResult;
import pers.xiaolin.result.Result;
import pers.xiaolin.service.UserMessageService;
import pers.xiaolin.dto.UserMessageDTO;
import pers.xiaolin.vo.UserMessageVO;

import javax.annotation.Resource;

/**
 * @author xiaolin03
 * @date 2025/5/30
 */
@Tag(name = "聊天记录内容")
@RestController
@RequestMapping("/user-message")
public class UserMessageController {
    @Resource
    private UserMessageService userMessageService;

    @PostMapping("/send")
    @Operation(summary = "发送聊天记录")
    public Result send(@RequestBody UserMessageDTO userMessageDTO) {
        userMessageService.send(userMessageDTO);
        return Result.success();
    }

    @PostMapping("/list")
    @Operation(summary = "查找聊天记录内容")
    public Result<PageResult<UserMessageVO>> list(@RequestBody UserMessageQueryDTO userMessageQueryDTO) {
        return Result.success(userMessageService.list(userMessageQueryDTO));
    }

    @PostMapping("/remove")
    @Operation(summary = "撤回聊天记录")
    public Result remove(@RequestBody UserMessageRemoveDTO userMessageRemoveDTO) {
        userMessageService.remove(userMessageRemoveDTO);
        return Result.success();
    }
}
