package com.dhrs.date.discuss.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhrs.date.common.exception.ErrCodeEnume;
import com.dhrs.date.common.utils.R;
import com.dhrs.date.discuss.entity.Comment;
import com.dhrs.date.discuss.entity.Discuss;
import com.dhrs.date.discuss.entity.InterestCircle;
import com.dhrs.date.discuss.service.DiscussService;
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
@RequestMapping("/discuss/dis")
public class DiscussController {
    @Autowired
    private DiscussService discussService;
    @PostMapping("add")
    public R addDiscuss(@RequestBody Discuss discuss){
        discussService.save(discuss);
        return R.ok("添加成功");
    }

    @DeleteMapping("delete/{discussId}/{uid}")
    public R deleteDiscuss(@PathVariable("discussId") String discussId
                          ,@PathVariable("uid") Long uid) {
        Discuss discuss = discussService.getById(discussId);
        if(discuss.getUid().equals(uid)){
            discussService.removeById(discussId);
            return R.ok("删除成功");
        }
        return R.error(ErrCodeEnume.WITHOUT_PERMISSION);

    }
    /**
     * 获取讨论区的所有讨论话题
     * */
    @GetMapping("selectall")
    public R selectAll(){
        QueryWrapper<Discuss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interest_id","0");
        List<Discuss> list = discussService.list(queryWrapper);
        return R.ok("查询所有讨论话题成功").put("discussList",list);
    }
    /**
     * 根据兴趣圈id获取所有讨论话题
     * */
    @GetMapping("selectbyinsid/{interestId}")
    public R selectAllCircleByInterestId(@PathVariable("interestId") String interestId){
        QueryWrapper<Discuss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interest_id",interestId);
        List<Discuss> list = discussService.list(queryWrapper);
        return R.ok("查询所有讨论话题成功").put("discussList",list);
    }

    @GetMapping("selectone/{discussId}")
    public R selectOne(@PathVariable("discussId") String discussId){
        Discuss discuss = discussService.getById(discussId);
        // 每获取一次该话题的具体内容，该讨论的浏览量就加1
        QueryWrapper<Discuss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",discussId);
        discuss.setBrowse(discuss.getBrowse()+1);
        discussService.update(discuss,queryWrapper);
        return R.ok("获取话题具体内容成功").put("discuss",discuss);
    }
    @PutMapping("update/{uid}")
    public R updateDiscuss(@RequestBody Discuss discuss,
                           @PathVariable("uid") Long uid){
        if(discuss.getUid().equals(uid)){
            discussService.updateById(discuss);
            return R.ok("更新成功");
        }
        return R.error(ErrCodeEnume.WITHOUT_PERMISSION);
    }

    /**
     * 给该讨论话题点赞
     * */
    @PutMapping("addlikes/{discussId}")
    public R addLikes(@PathVariable("discussId") String discussId){
        Discuss discuss = discussService.getById(discussId);
        discuss.setLikes(discuss.getLikes()+1);
        discussService.updateById(discuss);
        return R.ok("点赞成功");
    }
    /**
     * 根据id查询该话题所在的兴趣圈(0表示在讨论区)
     * */
    @GetMapping("selectcircle/{discussId}")
    public R selectCircle(@PathVariable("discussId") String discussId){
        InterestCircle interestCircle = discussService.selectCircleByDiscussId(discussId);
        if(interestCircle == null){
            return R.ok("该讨论话题是由讨论区发布");
        }
        return R.ok().put("interestCircle",interestCircle);
    }
    /**
     * 按最新发布排序查询(讨论区)
     * */
    @GetMapping("selectbytime")
    public R selectByTime(){
        List<Discuss> discussList = discussService.selectDiscussListByTime();
        return R.ok("按最新发布排序查询成功").put("discussListByTime",discussList);
    }
    /**
     * 按最热发布排序查询(讨论区)
     * */
    @GetMapping("selectbybrowse")
    public  R selectByBrowse(){
        List<Discuss> discussList = discussService.selectDiscussListByBrowse();
        return R.ok("按最热发布排序查询成功").put("discussListByBrowse",discussList);
    }
    /**
     * 按最新发布排序查询(兴趣圈)
     * */
    @GetMapping("selectbytime/circle/{interestId}")
    public R selectByTimeCircle(@PathVariable("interestId") String interestId){
        List<Discuss> discussList = discussService.selectDiscussListByTimeCircle(interestId);
        return R.ok("按最新发布排序查询成功").put("discussListByTime",discussList);
    }
    /**
     * 按最热发布排序查询(兴趣圈)
     * */
    @GetMapping("selectbybrowse/circle/{interestId}")
    public  R selectByBrowseCircle(@PathVariable("interestId") String interestId){
        List<Discuss> discussList = discussService.selectDiscussListByBrowseCircle(interestId);
        return R.ok("按最热发布排序查询成功").put("discussListByBrowse",discussList);
    }
    /**
     * 根据discussId查询回复数reply
     * */
    @GetMapping("selectreply/{discussId}")
    public R selectReplyById(@PathVariable("discussId") String discussId){
        Long reply = discussService.selectReplyNumById(discussId);
        return R.ok("获取回复数量成功").put("replyNum",reply);
    }

}

