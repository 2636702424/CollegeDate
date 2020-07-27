package com.dhrs.date.discuss.service;

import com.dhrs.date.common.entity.discussion.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxq
 * @since 2020-07-25
 */
public interface CommentService extends IService<Comment> {

    List<Comment> selectCommentListByTime(String discussId);

    List<Comment> selectCommentListByLike(String discussId);
}
