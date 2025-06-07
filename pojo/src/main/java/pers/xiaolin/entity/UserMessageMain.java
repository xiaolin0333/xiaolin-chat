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
 * 消息主表
 * @author xiaolin03
 * @date 2025/5/30
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserMessageMain {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /*发件人id*/
    private String senderId;
    /*收件人id*/
    private String receiverId;
    /*消息未读数*/
    private Integer messageCount;
    /*创建时间*/
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /*更新时间*/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
