package com.juno.gakebu.api.domain.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListApi<T> extends CommonApi {
    private List<T> list;
}
