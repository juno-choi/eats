package com.juno.eats.api.domain.common;

import lombok.Getter;

import java.util.List;

@Getter
public class ErrorApiV1<T> {
    private T error;

    public ErrorApiV1(T error) {
        this.error = error;
    }
}
