package com.juno.gakebu.api.controller.v1;

import com.juno.gakebu.api.domain.common.CommonApi;
import com.juno.gakebu.api.domain.common.SingleApi;
import com.juno.gakebu.api.service.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1.0 Test"})
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TestController {

    private final Response response;

    @ApiOperation(value = "테스트", notes = "테스트입니다.")
    @GetMapping("/test")
    public String Test(){
        return "Test";
    }

    @ApiOperation(value = "성공", notes = "성공입니다.")
    @GetMapping("/getSingle")
    public SingleApi getSingle(){
        return response.getSingleApi(new TestClass());
    }

    @Data
    class TestClass{
        int num = 1;
        String name = "test";
        int age = 20;
    }

}
