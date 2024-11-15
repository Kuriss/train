package com.kuriss.train.batch.feign;

import com.kuriss.train.common.resp.CommonResp;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class BusinessFeignFallback implements BusinessFeign{

    @Override
    public CommonResp<Object> genDaily(Date date) {
        return null;
    }

}
