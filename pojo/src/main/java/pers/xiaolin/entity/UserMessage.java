package pers.xiaolin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 消息内容
 * @author xiaolin03
 * @date 2025/5/30
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserMessage {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /*关联消息主表id*/
    private String messageId;
    /*消息内容*/
    private String content;
    /*发件人id*/
    private String senderId;
    /*收件人id*/
    private String receiverId;
    /*创建时间*/
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /*更新时间*/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
