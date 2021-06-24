package com.juno.eats.api.exception.login;

public class JoinFailException extends RuntimeException{
    private String msg;

    public JoinFailException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getFailValidation(){
        return msg;
    }
}
