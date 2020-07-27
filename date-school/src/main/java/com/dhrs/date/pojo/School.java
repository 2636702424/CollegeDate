package com.dhrs.date.pojo;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Data
@Table(name = "school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "school_id")
    private Long schoolId;
    private String name;//高校名称
    private String address;//地址
    private String content;//学校介绍
    private String logo;//学校logo
//    @Column(name = "city_name")
    private String cityName;
//    @Column(name = "create_date")
    private String createDate;//高校创建时间
//    @Column(name = "level_name")
    private String levelName;//办学等次--普通本科
//    @Column(name = "province_name")
    private String provinceName;
//    @Column(name = "school_nature_name")
    private String schoolNatureName;//办学性质--公办
    private int f211;//是否为211  0否1是
    private int f985;
    private String email;
    private String site;
//    @Column(name = "type_name")
    private String typeName;//文理性质
    private String belong;//隶属于
    private String area;//占地面积
    private String phone;//学校电话
    private String img;
    private int freq;//是否开启了高校论坛
    private String urban;//高校所在市区

}
