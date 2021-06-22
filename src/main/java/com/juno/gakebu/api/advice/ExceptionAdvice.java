package com.juno.gakebu.api.advice;

import com.juno.gakebu.api.domain.common.CommonApi;
import com.juno.gakebu.api.exception.JoinFailException;
import com.juno.gakebu.api.exception.LoginFailException;
import com.juno.gakebu.api.service.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.juno.gakebu.api.service.common.Response.*;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final Response response;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonApi defaultException(HttpServletRequest request, Exception e){
        return response.getFailResult(ApiResponse.FAIL.getCode(), ApiResponse.FAIL.getMsg());
    }

    /**
     * 로그인 실패
     */
    @ExceptionHandler(LoginFailException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonApi loginFailException(){
        return response.getFailResult(ApiResponse.LOGINFAIL.getCode(), ApiResponse.LOGINFAIL.getMsg());
    }

    /**
     * 회원가입 실패
     */
    @ExceptionHandler(JoinFailException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonApi joinFailException(){
        return response.getFailResult(ApiResponse.JOINFAIL.getCode(), ApiResponse.JOINFAIL.getMsg());
    }
}
