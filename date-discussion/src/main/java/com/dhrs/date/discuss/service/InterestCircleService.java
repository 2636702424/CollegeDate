package com.dhrs.date.discuss.service;

import com.dhrs.date.common.entity.discussion.InterestCircle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhrs.date.common.entity.discussion.vo.InterestCircleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxq
 * @since 2020-07-26
 */
public interface InterestCircleService extends IService<InterestCircle> {

    InterestCircleVo selectOne(String interestId);

    List<InterestCircleVo> selectBySortId(String sortId);

    Long totalDiscussNum(String interestId);
}
