package com.dhrs.date.user.service.impl;


import com.dhrs.date.common.utils.PageUtils;
import com.dhrs.date.common.utils.Query;
import com.dhrs.date.user.dao.MemberTagDao;
import com.dhrs.date.common.entity.user.MemberTagEntity;
import com.dhrs.date.user.service.MemberTagService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("memberTagService")
public class MemberTagServiceImpl extends ServiceImpl<MemberTagDao, MemberTagEntity> implements MemberTagService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberTagEntity> page = this.page(
                new Query<MemberTagEntity>().getPage(params),
                new QueryWrapper<MemberTagEntity>()
        );

        return new PageUtils(page);
    }

}