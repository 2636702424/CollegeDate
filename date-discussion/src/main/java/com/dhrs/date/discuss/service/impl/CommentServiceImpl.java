package com.dhrs.date.discuss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhrs.date.common.entity.discussion.Comment;
import com.dhrs.date.discuss.mapper.CommentMapper;
import com.dhrs.date.discuss.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxq
 * @since 2020-07-25
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> selectCommentListByTime(String discussId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        queryWrapper.eq("discuss_id",discussId);
        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public List<Comment> selectCommentListByLike(String discussId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("likes");
        queryWrapper.eq("discuss_id",discussId);
        return commentMapper.selectList(queryWrapper);
    }
}
