package com.dhrs.date.user.service.impl;


import com.dhrs.date.common.constant.SmsConstant;
import com.dhrs.date.common.utils.PageUtils;
import com.dhrs.date.common.utils.Query;
import com.dhrs.date.common.utils.R;
import com.dhrs.date.user.dao.MemberDao;
import com.dhrs.date.common.entity.user.MemberEntity;
import com.dhrs.date.user.feign.SmsFeign;
import com.dhrs.date.user.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    SmsFeign smsFeign;



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public int register(String phone) {
        int r = checkPhone(phone);
        if(r!=0) {
            //手机号错误
            return 1;
        }

        MemberEntity mobile = this.getOne(new QueryWrapper<MemberEntity>().eq("mobile", phone));
        if(mobile!=null) {
            //用户已经注册
            return 2;
        }
        //过期时间
        Long expire = redisTemplate.opsForValue().getOperations().getExpire(SmsConstant.SMS_REGISTER_PREFIX+phone,TimeUnit.SECONDS);
        if(expire<=240) {
            String s = StringUtils.leftPad(new Random().nextInt(100000) + "", 5, "0");
            R smsRes = smsFeign.sendVerifyCode(phone, s);
            if(smsRes.getCode()!=0) {
                return 3;
            }
            redisTemplate.opsForValue().set(SmsConstant.SMS_REGISTER_PREFIX+phone,s,5, TimeUnit.MINUTES);
        }
        return 0;
    }

    @Override
    public int save(String phone, String password,String code) {
        int r = checkPhone(phone);
        if(r!=0) {
            //手机号错误
            return 1;
        }
        String redisCode = redisTemplate.opsForValue().get(SmsConstant.SMS_REGISTER_PREFIX + phone);
        int i = checkPassword(password);
        if(i != 0) {
            //密码格式错误
            return 3;
        }
        if(StringUtils.isEmpty(redisCode) || !redisCode.equals(code)) {
            //验证码错误
            return 2;
        }
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMobile(phone);
        memberEntity.setPassword(new BCryptPasswordEncoder().encode(password));
        save(memberEntity);
        //删除验证码，防止重复注册
        redisTemplate.delete(SmsConstant.SMS_REGISTER_PREFIX+phone);
        return 0;
    }

    @Override
    public MemberEntity login(String phone, String password) {
        MemberEntity user = this.getOne(new QueryWrapper<MemberEntity>().eq("mobile", phone));
        if(user!=null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean matches = encoder.matches(password, user.getPassword());
            if (matches) {
                user.setPassword("");
                return user;
            }
        }
        return null;
    }

    @Override
    public int loginByPhone(String phone) {
        int r = checkPhone(phone);
        if(r!=0) {
            //手机号错误
            return 1;
        }
        MemberEntity mobile = this.getOne(new QueryWrapper<MemberEntity>().eq("mobile", phone));
        if(mobile==null) {
            //用户不存在
            return 2;
        }
        //过期时间
        Long expire = redisTemplate.opsForValue().getOperations().getExpire(SmsConstant.SMS_LOGIN_PREFIX+phone,TimeUnit.SECONDS);
        if(expire<=240) {
            String s = StringUtils.leftPad(new Random().nextInt(100000) + "", 5, "0");
            R smsRes = smsFeign.sendVerifyCode(phone, s);
            if(smsRes.getCode()!=0) {
                return 3;
            }
            redisTemplate.opsForValue().set(SmsConstant.SMS_LOGIN_PREFIX+phone,s,5, TimeUnit.MINUTES);
        }
        return 0;
    }

    @Override
    public MemberEntity loginByPhone(String phone, String code) {
        int r = checkPhone(phone);
        MemberEntity memberEntity = new MemberEntity();
        if(r!=0) {
            //手机号错误
            memberEntity.setId(-1L);
            return memberEntity;
        }
        String redisCode = redisTemplate.opsForValue().get(SmsConstant.SMS_LOGIN_PREFIX + phone);
        if(StringUtils.isEmpty(redisCode) || !redisCode.equals(code)) {
            //验证码错误
            memberEntity.setId(-2L);
            return memberEntity;
        }
        MemberEntity user = getOne(new QueryWrapper<MemberEntity>().eq("mobile", phone));
        //删除验证码
        redisTemplate.delete(SmsConstant.SMS_LOGIN_PREFIX+phone);
        return user;
    }

    private static int checkPassword(String password) {
        password.replace(""," ");
//        判断密码是否包含数字：包含返回1，不包含返回0
        int i = password.matches(".*\\d+.*") ? 1 : 0;
//        判断密码是否包含字母：包含返回1，不包含返回0
        int j = password.matches(".*[a-zA-Z]+.*") ? 1 : 0;
//        判断密码是否包含特殊符号(~!@#$%^&*()_+|<>,.?/:;'[]{}\)：包含返回1，不包含返回0
        int k = password.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*") ? 1 : 0;
//        判断密码长度是否在8-16位
        int l = password.length();
//        判断密码中是否包含用户名
        if (i + j + k < 2 || l < 8 || l > 16) {
            return 1;
        }else {
            return 0;
        }
    }

    private int checkPhone(String phone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if(phone.length() != 11){
            return 1;
        }else{
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if(isMatch){
                return  0;
            } else {
                return  1;
            }
        }
    }

}