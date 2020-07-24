package com.dhrs.date.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhrs.date.common.utils.PageUtils;
import com.dhrs.date.common.entity.user.MemberTagEntity;

import java.util.Map;

/**
 * 
 *
 * @author ly
 * @email 986352486@qq.com
 * @date 2020-07-24 14:20:58
 */
public interface MemberTagService extends IService<MemberTagEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

