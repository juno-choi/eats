package com.juno.eats.api.advice;

import com.juno.eats.api.domain.common.ErrorApiV1;
import com.juno.eats.api.exception.JoinFailException;
import com.juno.eats.api.exception.LoginFailException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorApiV1> defaultException(Exception e){
        List<ErrorResponse> errorList = new ArrayList<>();
        ErrorResponse error = new ErrorResponse("알수 없는 오류", "0500");
        errorList.add(error);
        return getErrorResponse(errorList, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 로그인 실패
     */
    @ExceptionHandler(LoginFailException.class)
    protected ResponseEntity<ErrorApiV1> loginFailException(){
        List<ErrorResponse> errorList = new ArrayList<>();
        ErrorResponse error = new ErrorResponse("인증에 실패했습니다.", "0101");
        errorList.add(error);
        return getErrorResponse(errorList, HttpStatus.BAD_REQUEST);
    }

    /**
     * 회원가입 실패
     */
    @ExceptionHandler(JoinFailException.class)
    protected ResponseEntity<ErrorApiV1> joinFailException(){
        List<ErrorResponse> errorList = new ArrayList<>();
        ErrorResponse error = new ErrorResponse("회원가입에 실패했습니다.", "0102");
        errorList.add(error);
        return getErrorResponse(errorList , HttpStatus.BAD_REQUEST);
    }

    @Getter
    class ErrorResponse{
        private String message;
        private String code;

        public ErrorResponse(String message, String code) {
            this.message = message;
            this.code = code;
        }
    }

    @NotNull
    private ResponseEntity<ErrorApiV1> getErrorResponse(List<ErrorResponse> errorList, HttpStatus status) {
        ErrorApiV1<List<ErrorResponse>> api = new ErrorApiV1(errorList);
        return new ResponseEntity<ErrorApiV1>(api, status);
    }
}
