package pers.xiaolin.service;

import pers.xiaolin.dto.UserMessageDTO;
import pers.xiaolin.dto.UserMessageQueryDTO;
import pers.xiaolin.dto.UserMessageRemoveDTO;
import pers.xiaolin.result.PageResult;
import pers.xiaolin.vo.UserMessageVO;

/**
 * @author xiaolin03
 * @date 2025/5/30
 */
public interface UserMessageService {
    /*发送聊天记录*/
    void send(UserMessageDTO userMessageDTO);
    /*查找聊天记录内容*/
    PageResult<UserMessageVO> list(UserMessageQueryDTO userMessageQueryDTO);
    /*撤回聊天记录*/
    void remove(UserMessageRemoveDTO userMessageRemoveDTO);
}
