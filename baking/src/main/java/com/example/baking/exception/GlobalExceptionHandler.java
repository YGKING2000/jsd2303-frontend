package com.example.baking.exception;

import com.example.baking.response.ResultVO;
import com.example.baking.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j // lombok提供的日志注解，在代码层面会为我们提供一个org.slf4j.Logger对象
@RestControllerAdvice //相当于@ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler({InternalAuthenticationServiceException.class, BadCredentialsException.class})
    public ResultVO handlerAuthenticationException(AuthenticationException e) {
        if (e instanceof InternalAuthenticationServiceException) {
            log.warn("用户名不存在!");
            return new ResultVO(StatusCode.USERNAME_ERROR);
        }
        log.warn("密码错误!");
        return new ResultVO(StatusCode.PASSWORD_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResultVO handler(AccessDeniedException e) {
        log.warn("无权访问!");
        return new ResultVO(StatusCode.FORBIDDEN_ERROR);
    }
}
