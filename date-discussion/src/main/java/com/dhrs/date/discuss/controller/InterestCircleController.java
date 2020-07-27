package com.dhrs.date.discuss.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhrs.date.common.exception.ErrCodeEnume;
import com.dhrs.date.common.utils.R;
import com.dhrs.date.common.entity.discussion.InterestCircle;
import com.dhrs.date.common.entity.discussion.vo.InterestCircleVo;
import com.dhrs.date.discuss.service.InterestCircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxq
 * @since 2020-07-26
 */
@RestController
@RequestMapping("/discuss/inscircle")
public class InterestCircleController {

    @Autowired
    private InterestCircleService interestCircleService;

    @PostMapping("add")
    public R addInsCircle(@RequestBody InterestCircle interestCircle){
        interestCircleService.save(interestCircle);
        return R.ok("添加成功");
    }

    @DeleteMapping("delete/{interestId}/{uid}")
    public R deleteById(@PathVariable("interestId") String interestId,
                        @PathVariable("uid") Long uid){
        InterestCircle interestCircle = interestCircleService.getById(interestId);
        if(!interestCircle.getUid().equals(uid)){
            return R.error(ErrCodeEnume.WITHOUT_PERMISSION);
        }
        interestCircleService.removeById(interestId);
        return R.ok("删除成功");
    }
    @GetMapping("selectone/{interestId}")
    public R selectOneById(@PathVariable("interestId") String interestId){
        InterestCircleVo interestCircleVo = interestCircleService.selectOne(interestId);
        return R.ok("查询成功").put("interestCircleVo",interestCircleVo);
    }
    @GetMapping("selectbysort/{sortId}")
    public R selectBySortId(@PathVariable("sortId") String sortId){
        List<InterestCircleVo> interestCircleVoList = interestCircleService.selectBySortId(sortId);
        return R.ok("查询成功").put("interestCircleVoList",interestCircleVoList);
    }
    /**
     * 统计该兴趣圈发布的话题数量
     * */
    @GetMapping("totalnum/{interestId}")
    public R totalDiscussNum(@PathVariable("interestId") String interestId){
        Long totalDiscussNum = interestCircleService.totalDiscussNum(interestId);
        return R.ok("查询成功").put("totalDiscussNum",totalDiscussNum);
    }
    @PutMapping("update/{uid}")
    public R updateById(@RequestBody InterestCircle interestCircle,
                        @PathVariable("uid") Long uid){
        if(!interestCircle.getUid().equals(uid)){
            return R.error(ErrCodeEnume.WITHOUT_PERMISSION);
        }
        QueryWrapper<InterestCircle> wrapper = new QueryWrapper<>();
        wrapper.eq("id",interestCircle.getId());
        interestCircleService.update(interestCircle,wrapper);
        return R.ok("修改成功");
    }
    /**
     * TODO
     * 关注该兴趣圈
     * */
    @GetMapping("likescircle/{interestId}/{uid}")
    public R likesCircle(@PathVariable("interestId") String interestId,
                         @PathVariable("uid") Long uid){
        return R.ok("查询成功");
    }

}

