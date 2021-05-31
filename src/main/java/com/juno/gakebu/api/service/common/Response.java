package com.juno.gakebu.api.service.common;

import com.juno.gakebu.api.domain.common.CommonApi;
import com.juno.gakebu.api.domain.common.ListApi;
import com.juno.gakebu.api.domain.common.SingleApi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Response {
    public enum ApiResponse{
        SUCCESS(0, "성공"), FAIL(-1, "실패")
        , LOGINFAIL(100, "아이디나 비밀번호가 일치하지 않습니다.")
        , JOINFAIL(101, "회원가입 유효성검사 FAIL");

        int code;
        String msg;

        ApiResponse(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    //단일 조회 처리
    public <T> SingleApi<T> getSingleApi(T data){
        SingleApi<T> result = new SingleApi<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    //다중 조회 처리
    public <T> ListApi<T> getListApi(List<T> list){
        ListApi<T> result = new ListApi<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    //성공 결과만 반환
    public CommonApi getSuccessResult(){
        CommonApi result = new CommonApi();
        setSuccessResult(result);
        return result;
    }

    //실패 결과만 반환
    public CommonApi getFailResult(int code, String msg){
        CommonApi result = new CommonApi();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    //성공 결과를 세팅해주는 메서드
    private void setSuccessResult(CommonApi result) {
        result.setSuccess(true);
        result.setCode(ApiResponse.SUCCESS.getCode());
        result.setMsg(ApiResponse.SUCCESS.getMsg());
    }
}
