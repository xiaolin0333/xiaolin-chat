package pers.xiaolin.service.impl;

import org.springframework.stereotype.Service;
import pers.xiaolin.context.BaseContext;
import pers.xiaolin.mapper.UserMessageMainMapper;
import pers.xiaolin.service.UserMessageMainService;
import pers.xiaolin.vo.UserMessageMainVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaolin03
 * @date 2025/5/30
 */
@Service
public class UserMessageMainServiceImpl implements UserMessageMainService {
    @Resource
    private UserMessageMainMapper userMessageMainMapper;
    /*查询聊天记录列表*/
    @Override
    public List<UserMessageMainVO> getMessageMainList() {
        String loginUserId = BaseContext.getCurrentId(); // 当前登录用户的id
        return userMessageMainMapper.getMessageMainList(loginUserId);
    }
}
