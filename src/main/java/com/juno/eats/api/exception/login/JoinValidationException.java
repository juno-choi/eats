package com.juno.eats.api.exception.login;

public class JoinValidationException extends RuntimeException{
    private String key;

    public JoinValidationException(String key) {
        super(key);
        this.key = key;
    }

    public String getValidation(){
        return key;
    }
}
