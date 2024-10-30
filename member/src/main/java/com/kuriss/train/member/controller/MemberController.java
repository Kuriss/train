package com.kuriss.train.member.controller;


import com.kuriss.train.common.resp.CommonResp;
import com.kuriss.train.member.entity.MemberLoginDto;
import com.kuriss.train.member.entity.MemberRegisterDto;
import com.kuriss.train.member.entity.MemberSendCodeDto;
import com.kuriss.train.member.resp.MemberLoginResp;
import com.kuriss.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public CommonResp<Long> register(@Valid @RequestBody MemberRegisterDto mrd) {
        return new CommonResp<>(memberService.register(mrd));
    }

    @PostMapping("/send-code")
    public CommonResp<Long> sendCode(@Valid @RequestBody MemberSendCodeDto msd) {
        memberService.sendCode(msd);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginDto mld) {
        MemberLoginResp memberLoginResp = memberService.login(mld);
        return new CommonResp<>(memberLoginResp);
    }
}

