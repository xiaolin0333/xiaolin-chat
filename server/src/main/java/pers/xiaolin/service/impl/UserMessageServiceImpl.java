package pers.xiaolin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.xiaolin.context.BaseContext;
import pers.xiaolin.dto.UserMessageQueryDTO;
import pers.xiaolin.dto.UserMessageRemoveDTO;
import pers.xiaolin.entity.UserMessage;
import pers.xiaolin.entity.UserMessageMain;
import pers.xiaolin.exception.ServiceException;
import pers.xiaolin.mapper.UserMessageMainMapper;
import pers.xiaolin.mapper.UserMessageMapper;
import pers.xiaolin.mqmessage.SendMessage;
import pers.xiaolin.mqmessage.SendMessageProducer;
import pers.xiaolin.result.PageResult;
import pers.xiaolin.service.UserMessageService;
import pers.xiaolin.dto.UserMessageDTO;
import pers.xiaolin.vo.UserMessageVO;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author xiaolin03
 * @date 2025/5/30
 */
@Service
public class UserMessageServiceImpl implements UserMessageService {
    @Resource
    private UserMessageMainMapper userMessageMainMapper;
    @Resource
    private UserMessageMapper userMessageMapper;
    @Resource
    private SendMessageProducer sendMessageProducer;
    /*发送聊天记录*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void send(UserMessageDTO userMessageDTO) {
        Assert.isTrue(StringUtils.isNotBlank(userMessageDTO.getContent()), ()->new ServiceException(1000, "无法发送空消息"));
        String senderId = BaseContext.getCurrentId(); // 发件人id(直接从ThreadLoca中获得)
        String receiverId = userMessageDTO.getReceiverId();
        // 1. 获取收发双方的对话主记录信息
        UserMessageMain userMessageMainDO = userMessageMainMapper.getUserMessageMain(senderId, receiverId);
        // 2. 维护消息主表信息
        if (userMessageMainDO == null) {// 没聊过天
            userMessageMainDO = new UserMessageMain().setMessageCount(1).setReceiverId(receiverId).setSenderId(senderId);
            Assert.isTrue(userMessageMainMapper.insert(userMessageMainDO) > 0, ()->new ServiceException(1000, "消息发送失败")); // 插入
        }else { // 聊过天
            userMessageMainDO.setMessageCount(userMessageMainDO.getMessageCount() + 1); // 未读数 + 1
            Assert.isTrue(userMessageMainMapper.updateById(userMessageMainDO) > 0, ()->new ServiceException(1000, "消息发送失败")); // 更新
        }
        // 3. 维护聊天记录表信息
        UserMessage userMessageDO = BeanUtil.copyProperties(userMessageDTO, UserMessage.class).setSenderId(senderId).setMessageId(userMessageMainDO.getId());
        Assert.isTrue(userMessageMapper.insert(userMessageDO) > 0, ()->new ServiceException(1000, "消息发送失败")); // 插入消息
        // 4. 向队列中新增消息
        try {
            SendMessage sendMessage = BeanUtil.copyProperties(userMessageDO, SendMessage.class);
            sendMessageProducer.sendMessage(sendMessage);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /*查找聊天记录内容*/
    @Override
    public PageResult<UserMessageVO> list(UserMessageQueryDTO userMessageQueryDTO) {
        String loginUserId = BaseContext.getCurrentId(); // 当前登录用户id
        String receiverId = userMessageQueryDTO.getReceiverId(); // 收件人id
        // 1. 处理未读数
        UserMessageMain userMessageMain = userMessageMainMapper.getUserMessageMain(receiverId, loginUserId);// 修改的应该是，receiverId给loginUserId发消息的未读数
        if(userMessageMain != null) {
            userMessageMain.setMessageCount(0); // 未读数变为0
            userMessageMainMapper.updateById(userMessageMain);
        }
        // 2. 查找聊天记录列表
        PageHelper.startPage(userMessageQueryDTO.getPageNo(), userMessageQueryDTO.getPageSize());
        Page<UserMessageVO> page = userMessageMapper.getUserMessageList(loginUserId, receiverId);
        List<UserMessageVO> list = page.getResult();
        Collections.reverse(list);
        PageResult<UserMessageVO> res = new PageResult<>(list, page.getTotal());
        return res;
    }

    /*撤回聊天记录*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(UserMessageRemoveDTO userMessageRemoveDTO) {
        // 1. 从聊天记录表中删除这条信息
        UserMessage userMessage = userMessageMapper.selectById(userMessageRemoveDTO.getId());
        Assert.isTrue(userMessage != null, ()->new ServiceException(1000, "消息不存在"));
        Assert.isTrue(Duration.between(userMessage.getCreateTime(), LocalDateTime.now()).toMinutes() < 3, ()->new ServiceException(1000, "超过3分钟的消息不能撤回"));
        Assert.isTrue(userMessageMapper.deleteById(userMessage.getId()) > 0, ()->new ServiceException(1000, "消息撤回失败"));
        // 2. 去查聊天记录主表，把未读数 - 1
        UserMessageMain userMessageMain = userMessageMainMapper.getUserMessageMain(userMessage.getSenderId(), userMessage.getReceiverId());
        userMessageMain.setMessageCount(Math.max(userMessageMain.getMessageCount() - 1, 0));
        userMessageMapper.updateById(userMessage);
    }
}
