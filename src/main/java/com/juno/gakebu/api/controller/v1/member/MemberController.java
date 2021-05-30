package com.juno.gakebu.api.controller.v1.member;

import com.juno.gakebu.api.controller.v1.TestController;
import com.juno.gakebu.api.domain.common.SingleApi;
import com.juno.gakebu.api.domain.member.Member;
import com.juno.gakebu.api.service.common.Response;
import com.juno.gakebu.api.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class MemberController {

    private final Response response;
    private final MemberService memberService;

    @GetMapping("/member/{id}")
    public SingleApi getMember(@PathVariable(value = "id") long id) throws Exception{
        return response.getSingleApi(memberService.findOne(id));
    }
}
