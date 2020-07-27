package com.dhrs.date.mapper;

import com.dhrs.date.common.entity.school.School;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SchoolMapper extends Mapper<School> {

    /**
     * 根据省份，城市，市区查询，条件可为空
     * @param province
     * @param city
     * @param urban
     * @return
     */
    @Select("select * from school where province_name like CONCAT('%',#{province},'%') and city_name like CONCAT('%',#{city},'%') and urban like CONCAT('%',#{urban},'%') LIMIT #{pageNum},#{pageSize}")
    public List<School> findByLocation(String province, String city, String urban,int pageNum,int pageSize);

    @Select("select * from shcool limit #{pageNum},#{pageSize}")
    public List<School> finAll(int pageNum,int pageSize);

}

