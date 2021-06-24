package com.juno.eats.api.exception.login;

public class KeyValidationException extends RuntimeException{
    private String key;

    public KeyValidationException(String key) {
        super(key);
        this.key = key;
    }

    public String getValidation(){
        return key;
    }
}
