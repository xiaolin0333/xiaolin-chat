package pers.xiaolin.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.xiaolin.exception.ServiceException;
import pers.xiaolin.result.Result;


/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(ServiceException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getCode(), ex.getMessage());
    }

}
