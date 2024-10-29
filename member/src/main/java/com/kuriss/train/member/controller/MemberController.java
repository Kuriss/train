package com.kuriss.train.member.controller;


import com.kuriss.train.common.resp.CommonResp;
import com.kuriss.train.member.entity.MemberRegisterDto;
import com.kuriss.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员 前端控制器
 * </p>
 *
 * @author Kuriss
 * @since 2024-10-30
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count() {
        CommonResp<Integer> commonResp = new CommonResp<>();
        commonResp.setContent(memberService.count());
        return commonResp ;
    }

    @PostMapping("/register")
    public CommonResp<Long> register(MemberRegisterDto mrd) {
        return new CommonResp<>(memberService.register(mrd));
    }
}

