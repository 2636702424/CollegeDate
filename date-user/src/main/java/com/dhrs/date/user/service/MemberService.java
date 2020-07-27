package com.dhrs.date.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhrs.date.common.utils.PageUtils;
import com.dhrs.date.common.entity.user.MemberEntity;

import java.util.Map;

/**
 * 
 *
 * @author ly
 * @email 986352486@qq.com
 * @date 2020-07-24 14:20:58
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    int register(String phone);


    int save(String phone, String password, String code);

    MemberEntity login(String phone, String password);

    int loginByPhone(String phone);

    MemberEntity loginByPhone(String phone, String code);

    int updateInfoById(String newPassword, String oldPassword, String id);

    int updatePhone(String phone);

    int updatePhoneCheck(String id, String phone, String code);
}

