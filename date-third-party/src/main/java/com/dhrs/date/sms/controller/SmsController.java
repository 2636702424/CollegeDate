package com.dhrs.date.sms.controller;

import com.dhrs.date.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SmsController {

    @Autowired
    private SmsService smsService;


    /**
     * 发送手机验证码
     * @param phone
     * @return
     */
    @GetMapping("msg")
    public ResponseEntity<Void> sendVerifyCode(String phone,String msg) {
        System.out.println(phone);
        Boolean boo = this.smsService.sendVerifyCode(phone,msg);
        if (boo == null || !boo) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
