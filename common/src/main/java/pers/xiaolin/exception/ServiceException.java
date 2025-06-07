package pers.xiaolin.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 业务逻辑异常 Exception
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public final class ServiceException extends RuntimeException {
    /**
     * 业务错误码
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;
}
