package com.juno.eats.api.advice;

import com.juno.eats.api.domain.common.CommonApiV1;
import com.juno.eats.api.exception.JoinFailException;
import com.juno.eats.api.exception.LoginFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonApiV1> defaultException(Exception e){
        CommonApiV1<String> api = new CommonApiV1(false, "0500", "알수 없는 오류", "null");
        return new ResponseEntity<CommonApiV1>(api, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 로그인 실패
     */
    @ExceptionHandler(LoginFailException.class)
    protected ResponseEntity<CommonApiV1> loginFailException(){
        CommonApiV1<String> api = new CommonApiV1(false, "0000", "로그인 실패", "null");
        return new ResponseEntity<CommonApiV1>(api, HttpStatus.OK);
    }

    /**
     * 회원가입 실패
     */
    @ExceptionHandler(JoinFailException.class)
    protected ResponseEntity<CommonApiV1> joinFailException(){
        CommonApiV1<String> api = new CommonApiV1(false, "0000", "회원가입 실패", "null");
        return new ResponseEntity<CommonApiV1>(api, HttpStatus.OK);
    }
}
