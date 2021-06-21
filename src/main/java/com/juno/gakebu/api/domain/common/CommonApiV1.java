package com.juno.gakebu.api.domain.common;

import lombok.Getter;

@Getter
public class CommonApiV1<T> {
    private boolean success;
    private String code;
    private String msg;
    private T data;

    public CommonApiV1(boolean success, String code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
