package pers.xiaolin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xiaolin03
 * @date 2025/5/28
 */
@Data
@Accessors(chain = true)
@Schema(description = "用户登录返回值")
public class UserLoginVO implements Serializable {
    @Schema(description = "主键")
    private String id;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "token")
    private String token;
}