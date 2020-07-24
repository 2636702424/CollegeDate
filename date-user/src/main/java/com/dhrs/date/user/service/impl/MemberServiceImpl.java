package com.dhrs.date.user.service.impl;


import com.dhrs.date.common.utils.PageUtils;
import com.dhrs.date.common.utils.Query;
import com.dhrs.date.user.dao.MemberDao;
import com.dhrs.date.common.entity.user.MemberEntity;
import com.dhrs.date.user.service.MemberService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

}