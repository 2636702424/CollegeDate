package com.dhrs.date.user.controller;

import java.util.Arrays;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhrs.date.common.exception.ErrCodeEnume;
import com.dhrs.date.common.utils.JwtUtil;
import com.dhrs.date.common.utils.PageUtils;
import com.dhrs.date.common.utils.R;
import com.dhrs.date.common.entity.user.MemberEntity;
import com.dhrs.date.user.service.MemberService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 *
 * @author ly
 * @email 986352486@qq.com
 * @date 2020-07-24 14:20:58
 */
@RestController
@RequestMapping("user/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 列表
     */

    @PostMapping("/guest/register/{phone}")
    public R register(@PathVariable("phone") String phone) {
        int r = memberService.register(phone);
        if(r == 1) {
            return R.error(ErrCodeEnume.PHONE_INVAILD_EXCEPTION);
        }
        if(r == 2) {
            return R.error(ErrCodeEnume.USER_EXIST_EXCEPTION);
        }
        if(r == 3) {
            return R.error(ErrCodeEnume.MESSGAE_SEND_FAIL);
        }
        return R.ok();
    }

    /**
     * 保存
     */
    @PostMapping("/guest/save")
    public R save(@RequestBody Map<String,String> map){
        String phone = map.get("phone");
        String password = map.get("password");
        String code = map.get("code");
        int r = memberService.save(phone,password,code);
        if(r == 1) {
            return R.error(ErrCodeEnume.PHONE_INVAILD_EXCEPTION);
        }
        if(r == 2) {
            return R.error(ErrCodeEnume.CHECK_CODE_INVAILD_EXCEPTION);
        }
        if(r == 3) {
            return R.error(ErrCodeEnume.VAILD_EXCEPTION);
        }
        return R.ok();
    }

    @PostMapping("/guest/login")
    public R login(@RequestBody Map<String,String> map) {
        String phone = map.get("phone");
        String password = map.get("password");
        MemberEntity user = memberService.login(phone, password);
        if(user != null) {
            String token = JwtUtil.createJWT(user.getId() + "", user.getMobile(), user);
            return R.ok().put("user",user).put("token",token);
        }else {
            return R.error(ErrCodeEnume.LOGINACCT_PASSWORD_INVAILD_EXCEPTION);
        }
    }


    @PostMapping("/guest/login/{phone}")
    public R login(@PathVariable("phone") String phone){
        int r = memberService.loginByPhone(phone);
        if(r == 1) {
            return R.error(ErrCodeEnume.PHONE_INVAILD_EXCEPTION);
        }
        if(r == 2) {
            return R.error(ErrCodeEnume.USER_NOT_EXEIST);
        }
        if(r == 3) {
            return R.error(ErrCodeEnume.MESSGAE_SEND_FAIL);
        }
        return R.ok();
    }

    @GetMapping("/guest/login/{phone}/{code}")
    public R login(@PathVariable("phone") String phone,@PathVariable("code")  String code) {
        MemberEntity user = memberService.loginByPhone(phone,code);
        if(user != null) {
            if(user.getId().equals(-1L)) {
                return R.error(ErrCodeEnume.PHONE_INVAILD_EXCEPTION);
            }
            if(user.getId().equals(-2L)) {
                return R.error(ErrCodeEnume.CHECK_CODE_INVAILD_EXCEPTION);
            }
            String token = JwtUtil.createJWT(user.getId() + "", user.getMobile(), user);
            return R.ok().put("user",user).put("token",token);
        }else {
            return R.error(ErrCodeEnume.USER_NOT_EXEIST);
        }
    }



    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/guest/info/{id}")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);
        member.setPassword("");
        return R.ok().put("member", member);
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberEntity member,HttpServletRequest request){
        member.setPassword(null);
        member.setMobile(null);
        member.setAvatar(null);
        member.setStatus(null);
        Long uid = JwtUtil.getUserId(request);
        if(uid.equals(member.getId())) {
            memberService.updateById(member);
        }
        return R.ok();
    }

    @RequestMapping("/update/password")
    public R update(@RequestBody Map<String,String> map,HttpServletRequest request){
        String newPassword = map.get("new");
        String oldPassword = map.get("old");
        String id = map.get("id");
        String uid = JwtUtil.getUserId(request).toString();
        if(uid.equals(id)) {
            int i = memberService.updateInfoById(newPassword, oldPassword, id);
            if(i!=0) {
                return R.error(ErrCodeEnume.VAILD_EXCEPTION);
            }
        }
        return R.ok();
    }


    @PostMapping("/update/phone/{phone}")
    public R updatePhoneSend(@PathVariable String phone,HttpServletRequest request) {
        int r = memberService.updatePhone(phone);
        if(r == 1) {
            return R.error(ErrCodeEnume.PHONE_INVAILD_EXCEPTION);
        }
        if(r == 2) {
            return R.error(ErrCodeEnume.USER_EXIST_EXCEPTION);
        }
        if(r == 3) {
            return R.error(ErrCodeEnume.MESSGAE_SEND_FAIL);
        }
        return R.ok();
    }

    @RequestMapping("/update/phone/check/{phone}/{code}")
    public R updatePhone(@PathVariable String phone,@PathVariable String code,HttpServletRequest request) {
        String uid = JwtUtil.getUserId(request).toString();
        int r = memberService.updatePhoneCheck(uid,phone,code);
        if(r == 1) {
            return R.error(ErrCodeEnume.PHONE_INVAILD_EXCEPTION);
        }
        if(r == 2) {
            return R.error(ErrCodeEnume.CHECK_CODE_INVAILD_EXCEPTION);
        }
        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }



}
