package com.dhrs.date.discuss.service;

import com.dhrs.date.discuss.entity.Comment;
import com.dhrs.date.discuss.entity.Discuss;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhrs.date.discuss.entity.InterestCircle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxq
 * @since 2020-07-25
 */

public interface DiscussService extends IService<Discuss> {


    List<Comment> getAllCommentsByDiscussId(String discussId);

    InterestCircle selectCircleByDiscussId(String discussId);

    List<Discuss> selectDiscussListByTime();

    List<Discuss> selectDiscussListByBrowse();

    Long selectReplyNumById(String discussId);

    List<Discuss> selectDiscussListByTimeCircle(String interestId);

    List<Discuss> selectDiscussListByBrowseCircle(String interestId);
}
