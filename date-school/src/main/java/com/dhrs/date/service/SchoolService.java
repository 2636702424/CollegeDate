package com.dhrs.date.service;

//import com.dhrs.date.common.entity.user.MemberEntity;
import com.dhrs.date.pojo.School;
import com.dhrs.date.pojo.resp.PageResult;

import java.util.List;

public interface SchoolService {

    /**
     * 根据id查询学校
     * @param id
     * @return
     */
    School findById(Long id);

    /**
     * 根据区域分页查询相关高校
     * @param province
     * @param city
     * @param urban
     * @param pageNum
     * @return
     */
    PageResult findByLocation(String province, String city, String urban, int pageNum);

    /**
     * 查询所有高校
     * @param pageNum
     * @return
     */
    PageResult findAll(int pageNum);

    /**
     * 添加学校
     * @param school
     */
    void insert(School school);

}
