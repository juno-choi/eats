package com.juno.gakebu.api.domain.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleApi<T> extends CommonApi {
    private T data;
}
