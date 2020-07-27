package com.dhrs.date.discuss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhrs.date.common.entity.discussion.Comment;
import com.dhrs.date.common.entity.discussion.Discuss;
import com.dhrs.date.common.entity.discussion.InterestCircle;
import com.dhrs.date.common.entity.discussion.Reply;
import com.dhrs.date.discuss.mapper.CommentMapper;
import com.dhrs.date.discuss.mapper.DiscussMapper;
import com.dhrs.date.discuss.mapper.InterestCircleMapper;
import com.dhrs.date.discuss.mapper.ReplyMapper;
import com.dhrs.date.discuss.service.DiscussService;
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
public class DiscussServiceImpl extends ServiceImpl<DiscussMapper, Discuss> implements DiscussService {
    @Autowired
    private DiscussMapper discussMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private InterestCircleMapper interestCircleMapper;
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public List<Comment> getAllCommentsByDiscussId(String discussId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("discuss_id",discussId);
        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public InterestCircle selectCircleByDiscussId(String discussId) {
        Discuss discuss = discussMapper.selectById(discussId);
        if (discuss.getInterestId().equals("0")){
            return null;
        }
        return interestCircleMapper.selectById(discuss.getInterestId());
    }

    @Override
    public List<Discuss> selectDiscussListByTime() {
        QueryWrapper<Discuss> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        queryWrapper.eq("interest_id","0");
        return discussMapper.selectList(queryWrapper);
    }

    @Override
    public List<Discuss> selectDiscussListByBrowse() {
        QueryWrapper<Discuss> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("browse");
        queryWrapper.eq("interest_id","0");
        return discussMapper.selectList(queryWrapper);
    }

    @Override
    public Long selectReplyNumById(String discussId) {
        // 获取该讨论话题的评论数量
        QueryWrapper<Comment> queryWrapperComment = new QueryWrapper();
        queryWrapperComment.eq("discuss_id",discussId);
        Integer commentCount = commentMapper.selectCount(queryWrapperComment);
        // 获取该该讨论话题的回复数量
        List<Comment> commentList = commentMapper.selectList(queryWrapperComment);
        Integer replyCount = 0;
        for (Comment comment : commentList) {
            QueryWrapper<Reply> queryWrapperReply = new QueryWrapper();
            queryWrapperReply.eq("comment_id",comment.getId());
            replyCount += replyMapper.selectCount(queryWrapperReply);
        }
        Long replyNum = Integer.toUnsignedLong(commentCount + replyCount);
        Discuss discuss = discussMapper.selectById(discussId);

        if(!discuss.getReply().equals(replyNum)){
            discuss.setReply(replyNum);
            discussMapper.updateById(discuss);
        }
        return replyNum;
    }

    @Override
    public List<Discuss> selectDiscussListByTimeCircle(String interestId) {
        QueryWrapper<Discuss> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        queryWrapper.eq("interest_id",interestId);
        return discussMapper.selectList(queryWrapper);
    }

    @Override
    public List<Discuss> selectDiscussListByBrowseCircle(String interestId) {
        QueryWrapper<Discuss> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("browse");
        queryWrapper.eq("interest_id",interestId);
        return discussMapper.selectList(queryWrapper);
    }
}
