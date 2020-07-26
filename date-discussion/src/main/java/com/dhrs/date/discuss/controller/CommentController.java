package com.dhrs.date.discuss.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhrs.date.common.exception.ErrCodeEnume;
import com.dhrs.date.common.utils.R;
import com.dhrs.date.discuss.entity.Comment;
import com.dhrs.date.discuss.entity.Discuss;
import com.dhrs.date.discuss.entity.InterestCircle;
import com.dhrs.date.discuss.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxq
 * @since 2020-07-25
 */
@RestController
@RequestMapping("/discuss/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("add")
    public R addDiscuss(@RequestBody Comment comment){
        commentService.save(comment);
        return R.ok("添加成功");
    }
    @DeleteMapping("delete/{commentId}/{uid}")
    public R deleteDiscuss(@PathVariable("commentId") String commentId,
                           @PathVariable("uid") Long uid) {
        Comment comment = commentService.getById(commentId);
        if(comment.getUid().equals(uid)){
            commentService.removeById(commentId);
            return R.ok("删除成功");
        }
        return R.error(ErrCodeEnume.WITHOUT_PERMISSION);

    }

    /**
     * 根据discussId查询该讨论话题对应的评论
     * */
    @GetMapping("getallcomments/{discussId}")
    public R getAllComments(@PathVariable("discussId") String discussId){
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("discuss_id",discussId);
        List<Comment> commentList = commentService.list(wrapper);
        return R.ok("获取成功").put("commentList",commentList);
    }
    @GetMapping("selectone/{commentId}")
    public R selectOne(@PathVariable("commentId") String commentId){
        Comment comment = commentService.getById(commentId);
        return R.ok("获取一条评论成功").put("comment",comment);
    }
    @PutMapping("update/{uid}")
    public R updateDiscuss(@RequestBody Comment comment,
                           @PathVariable("uid") Long uid){
        if(comment.getUid().equals(uid)){
            commentService.updateById(comment);
            return R.ok("更新成功");
        }
        return R.error(ErrCodeEnume.WITHOUT_PERMISSION);
    }

    /**
     * 给该条评论点赞
     * */
    @PutMapping("addlikes/{commentId}")
    public R addLikes(@PathVariable("commentId") String commentId){
        Comment comment = commentService.getById(commentId);
        comment.setLikes(comment.getLikes()+1);
        commentService.updateById(comment);
        return R.ok("点赞成功");
    }

    /**
     * 按最新发布排序查询
     * */
    @GetMapping("selectbytime/{discussId}")
    public R selectByTime(@PathVariable("discussId") String discussId){
        List<Comment> commentList = commentService.selectCommentListByTime(discussId);
        return R.ok("按最新发布排序查询成功").put("commentList",commentList);
    }
    /**
     * 按最热发布排序查询
     * */
    @GetMapping("selectbylike/{discussId}")
    public  R selectByBrowse(@PathVariable("discussId") String discussId){
        List<Comment> commentList = commentService.selectCommentListByLike(discussId);
        return R.ok("按最热发布排序查询成功").put("commentList",commentList);
    }


}

