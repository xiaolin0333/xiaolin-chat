package pers.xiaolin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import pers.xiaolin.entity.UserMessage;
import pers.xiaolin.vo.UserMessageVO;

/**
 * @author xiaolin03
 * @date 2025/5/30
 */
@Mapper
public interface UserMessageMapper extends BaseMapper<UserMessage> {
    Page<UserMessageVO> getUserMessageList(String senderId, String receiverId);
}
