package com.dhrs.date.discuss.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhrs.date.common.exception.ErrCodeEnume;
import com.dhrs.date.common.utils.R;
import com.dhrs.date.common.entity.discussion.Reply;
import com.dhrs.date.discuss.service.ReplyService;
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
@RequestMapping("/discuss/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    @PostMapping("add")
    public R addDiscuss(@RequestBody Reply reply){
        replyService.save(reply);
        return R.ok("添加成功");
    }
    @DeleteMapping("delete/{replyId}/{uid}")
    public R deleteDiscuss(@PathVariable("replyId") String replyId,
                           @PathVariable("uid") Long uid) {
        Reply reply = replyService.getById(replyId);
        if(reply.getUid().equals(uid)){
            replyService.removeById(replyId);
            QueryWrapper<Reply> replyQueryWrapper = new QueryWrapper<>();
            replyQueryWrapper.eq("parent_id",replyId);
            replyService.remove(replyQueryWrapper);
            return R.ok("删除成功");
        }
        return R.error(ErrCodeEnume.WITHOUT_PERMISSION);

    }
    /**
     * 根据commentId查询所有一级回复
     * */
    @GetMapping("selectallone/{commentId}")
    public R selectAllOne(@PathVariable("commentId") String commentId){

        List<Reply> replyList = replyService.selectAllOne(commentId);
        return R.ok("查询所有一级回复成功").put("replyList",replyList);
    }
    /**
     * 根据replyId查询所有二级回复
     * */
    @GetMapping("selectalltwo/{replyId}")
    public R selectAllTwo(@PathVariable("replyId") String replyId){
        List<Reply> replyList = replyService.selectAllTwo(replyId);
        return R.ok("查询对应的二级回复成功").put("replyList",replyList);
    }

    @PutMapping("update/{uid}")
    public R updateDiscuss(@RequestBody Reply reply,
                           @PathVariable("uid") Long uid){
        if(reply.getUid().equals(uid)){
            replyService.updateById(reply);
            return R.ok("更新成功");
        }
        return R.error(ErrCodeEnume.WITHOUT_PERMISSION);
    }

}

