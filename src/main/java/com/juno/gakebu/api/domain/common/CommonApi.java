package com.juno.gakebu.api.domain.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonApi {
    @ApiModelProperty(value = "응답 여부 boolean")
    private boolean success;
    @ApiModelProperty(value = "응답 코드")
    private int code;
    @ApiModelProperty(value = "응답 메세지")
    private String msg;
}
