package com.dhrs.date.discuss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhrs.date.common.entity.discussion.Reply;
import com.dhrs.date.discuss.mapper.ReplyMapper;
import com.dhrs.date.discuss.service.ReplyService;
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
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public List<Reply> selectAllOne(String commentId) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id",commentId);
        queryWrapper.eq("parent_id","0");
        return replyMapper.selectList(queryWrapper);
    }

    @Override
    public List<Reply> selectAllTwo(String replyId) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",replyId);
        return replyMapper.selectList(queryWrapper);
    }
}
