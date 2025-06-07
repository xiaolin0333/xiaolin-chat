package pers.xiaolin.service;

import pers.xiaolin.vo.UserMessageMainVO;

import java.util.List;

/**
 * @author xiaolin03
 * @date 2025/5/30
 */
public interface UserMessageMainService {
    /*查询聊天记录列表*/
    List<UserMessageMainVO> getMessageMainList();
}
