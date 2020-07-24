package com.dhrs.date.user.service.impl;

import com.dhrs.date.common.utils.PageUtils;
import com.dhrs.date.common.utils.Query;
import com.dhrs.date.user.dao.TagDao;
import com.dhrs.date.common.entity.user.TagEntity;
import com.dhrs.date.user.service.TagService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagDao, TagEntity> implements TagService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TagEntity> page = this.page(
                new Query<TagEntity>().getPage(params),
                new QueryWrapper<TagEntity>()
        );

        return new PageUtils(page);
    }

}