package pers.xiaolin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.xiaolin.entity.UserMessageMain;
import pers.xiaolin.vo.UserMessageMainVO;

import java.util.List;

/**
 * @author xiaolin03
 * @date 2025/5/30
 */
@Mapper
public interface UserMessageMainMapper extends BaseMapper<UserMessageMain> {
    /*查看senderId这个用户有没有给receiverId这个用户发过消息*/
    UserMessageMain getUserMessageMain(String senderId, String receiverId);

    /*获取聊天记录列表*/
    List<UserMessageMainVO> getMessageMainList(String userId);
    /*我给谁发消息*/
    List<UserMessageMainVO> getMessageMainList1(String userId);
    /*谁给我发消息*/
    List<UserMessageMainVO> getMessageMainList2(String userId);
}
