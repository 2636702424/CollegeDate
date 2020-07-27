package com.dhrs.date.discuss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhrs.date.common.entity.discussion.CircleSort;
import com.dhrs.date.common.entity.discussion.Discuss;
import com.dhrs.date.common.entity.discussion.InterestCircle;
import com.dhrs.date.common.entity.discussion.vo.InterestCircleVo;
import com.dhrs.date.discuss.mapper.CircleSortMapper;
import com.dhrs.date.discuss.mapper.DiscussMapper;
import com.dhrs.date.discuss.mapper.InterestCircleMapper;
import com.dhrs.date.discuss.service.InterestCircleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxq
 * @since 2020-07-26
 */
@Service
public class InterestCircleServiceImpl extends ServiceImpl<InterestCircleMapper, InterestCircle> implements InterestCircleService {

    @Autowired
    private InterestCircleMapper interestCircleMapper;
    @Autowired
    private CircleSortMapper circleSortMapper;
    @Autowired
    private DiscussMapper discussMapper;

    @Override
    public InterestCircleVo selectOne(String interestId) {
        InterestCircle interestCircle = interestCircleMapper.selectById(interestId);
        InterestCircleVo interestCircleVo = new InterestCircleVo();
        BeanUtils.copyProperties(interestCircle,interestCircleVo);
        CircleSort circleSort = circleSortMapper.selectById(interestCircle.getSortId());
        interestCircleVo.setSortName(circleSort.getName());
        return interestCircleVo;
    }

    @Override
    public List<InterestCircleVo> selectBySortId(String sortId) {
        QueryWrapper<InterestCircle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sort_id",sortId);
        List<InterestCircle> interestCircleList = interestCircleMapper.selectList(queryWrapper);
        List<InterestCircleVo> interestCircleVoList = new ArrayList<>();
        for (InterestCircle interestCircle : interestCircleList) {
            InterestCircleVo interestCircleVo = new InterestCircleVo();
            BeanUtils.copyProperties(interestCircle,interestCircleVo);
            CircleSort circleSort = circleSortMapper.selectById(interestCircle.getSortId());
            interestCircleVo.setSortName(circleSort.getName());
            interestCircleVoList.add(interestCircleVo);
        }
        return interestCircleVoList;
    }

    @Override
    public Long totalDiscussNum(String interestId) {
        QueryWrapper<Discuss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interest_id",interestId);
        Long discussNum = discussMapper.selectCount(queryWrapper).longValue();
        InterestCircle interestCircle = interestCircleMapper.selectById(interestId);
        if(!interestCircle.getDiscussNum().equals(discussNum)){
            interestCircle.setDiscussNum(discussNum);
            interestCircleMapper.updateById(interestCircle);
        }
        return discussNum;
    }
}
