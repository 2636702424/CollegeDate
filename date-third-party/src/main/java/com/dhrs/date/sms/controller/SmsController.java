//package com.dhrs.date.sms.controller;
//
//import com.dhrs.date.common.exception.ErrCodeEnume;
//import com.dhrs.date.common.utils.R;
//import com.dhrs.date.sms.service.SmsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/sms")
//public class SmsController {
//
//    @Autowired
//    private SmsService smsService;
//
//
//    /**
//     * 发送手机验证码
//     * @param phone
//     * @return
//     */
//    @GetMapping("/msg/{phone}/{msg}")
//    public R sendVerifyCode(@PathVariable("phone") String phone, @PathVariable("msg") String msg) {
//        System.out.println(phone);
//        Boolean boo = this.smsService.sendVerifyCode(phone,msg);
//        if (boo == null || !boo) {
//            return R.error(ErrCodeEnume.MESSGAE_SEND_FAIL);
//        }
//        return R.ok();
//    }
//}
