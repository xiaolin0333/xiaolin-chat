package pers.xiaolin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xiaolin03
 * @date 2025/5/28
 */
@Data
@Schema(description = "用户登录传递数据")
public class UserLoginDTO {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;
}
