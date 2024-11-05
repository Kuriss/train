package com.kuriss.train.member.controller;

import com.kuriss.train.common.context.LoginMemberContext;
import com.kuriss.train.common.resp.CommonResp;
import com.kuriss.train.common.resp.PageResp;
import com.kuriss.train.member.entity.PassengerQueryDto;
import com.kuriss.train.member.entity.PassengerSaveDto;
import com.kuriss.train.member.resp.PassengerQueryResp;
import com.kuriss.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> register(@Valid @RequestBody PassengerSaveDto psd) {
        passengerService.save(psd);
        return new CommonResp<>();
    }
    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryDto pqd) {
        pqd.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> list = passengerService.queryList(pqd);
        return new CommonResp<>(list);
    }
    @DeleteMapping("/delete/{id}")
    public CommonResp<Object>delete(@PathVariable Long id) {
        passengerService.delete(id);
        return new CommonResp<>();
    }
}

