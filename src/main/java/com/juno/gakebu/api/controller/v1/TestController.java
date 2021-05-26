package com.juno.gakebu.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1.0 Test"})
@RestController
@RequestMapping("/v1")
public class TestController {


    @ApiOperation(value = "테스트", notes = "테스트입니다.")
    @GetMapping("/test")
    public String Test(){
        return "Test";
    }

}
