package com.dhrs.date.user.controller;

import java.util.Arrays;
import java.util.Map;

import com.dhrs.date.common.utils.PageUtils;
import com.dhrs.date.common.utils.R;
import com.dhrs.date.common.entity.user.MemberTagEntity;
import com.dhrs.date.user.service.MemberTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * 
 *
 * @author ly
 * @email 986352486@qq.com
 * @date 2020-07-24 14:20:58
 */
@RestController
@RequestMapping("user/membertag")
public class MemberTagController {
    @Autowired
    private MemberTagService memberTagService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberTagService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		MemberTagEntity memberTag = memberTagService.getById(id);

        return R.ok().put("memberTag", memberTag);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberTagEntity memberTag){
		memberTagService.save(memberTag);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberTagEntity memberTag){
		memberTagService.updateById(memberTag);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		memberTagService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
