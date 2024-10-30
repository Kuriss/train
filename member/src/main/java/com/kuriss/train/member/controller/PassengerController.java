package com.kuriss.train.member.controller;

import com.kuriss.train.common.resp.CommonResp;
import com.kuriss.train.member.entity.PassengerSaveDto;
import com.kuriss.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

