package com.dhrs.date.sms.service;

import com.dhrs.date.sms.config.SmsProperties;
import com.dhrs.date.sms.utils.SmsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SmsService {

    @Autowired
    private SmsUtils smsUtils;
    @Autowired
    private SmsProperties smsProperties;

    public Boolean sendVerifyCode(String phone,String msg) {
        try {
            if(StringUtils.isNoneBlank(phone)&&StringUtils.isNoneBlank(msg))
            {
                try {
                    smsUtils.sendSms(phone,msg,smsProperties.getSignName(),smsProperties.getVerifyCodeTemplate());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
            return true;
        } catch (Exception e) {
            System.err.println("发送短信失败。phone：{}， msg：{}"+phone+" "+msg);
            return false;
        }
    }
}
