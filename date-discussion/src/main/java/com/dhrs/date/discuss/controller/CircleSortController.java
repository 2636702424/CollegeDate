package com.dhrs.date.discuss.controller;


import com.dhrs.date.common.utils.R;
import com.dhrs.date.common.entity.discussion.CircleSort;
import com.dhrs.date.discuss.service.CircleSortService;
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
@RequestMapping("/discuss/sort")
public class CircleSortController {
    @Autowired
    private CircleSortService circleSortService;

    @PostMapping("add")
    public R addCircleSort(@RequestBody CircleSort circleSort){
        circleSortService.save(circleSort);
        return R.ok("添加成功");
    }
    @DeleteMapping("delete/{sortId}")
    public R deleteBySortId(@PathVariable("sortId") String sortId){
        circleSortService.removeById(sortId);
        return R.ok("删除成功");
    }
    @GetMapping("selectall")
    public R selectAll(){
        List<CircleSort> circleSortList = circleSortService.list(null);
        return R.ok("获取所有分类成功").put("circleSortList",circleSortList);
    }
    /**
     * 根据sortId查询分类
     * */
    @GetMapping("selectone/{sortId}")
    public R selectOne(@PathVariable("sortId") String sortId){
        CircleSort circleSort = circleSortService.getById(sortId);
        return R.ok("查询成功").put("circleSort",circleSort);
    }
    /**
     * 根据sortId修改分类
     * */
    @PutMapping("update")
    public R updateById(@RequestBody CircleSort circleSort){
        circleSortService.updateById(circleSort);
        return R.ok("修改成功");
    }

}

