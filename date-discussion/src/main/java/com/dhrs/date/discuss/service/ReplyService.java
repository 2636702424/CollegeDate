package com.dhrs.date.discuss.service;

import com.dhrs.date.discuss.entity.Reply;
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
public interface ReplyService extends IService<Reply> {

    List<Reply> selectAllOne(String commentId);

    List<Reply> selectAllTwo(String replyId);
}
