package com.dhrs.date.common.entity.discussion.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author zxq
 * @Date 2020/7/25 17:23
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterestCircleVo  {

    /**
     * 创建兴趣圈的用户id
     */
    private Long uid;
    /**
     * 圈名称
     */
    private String circleName;

    /**
     * 兴趣圈图标
     */
    private String avatar;
    /**
     * 兴趣圈简介
     */
    private String comment;

    /**
     * 兴趣圈类型
     */
    private String sortName;

    /**
     * 关注该圈的用户数量
     */
    private Long fansNum;

    /**
     * 该圈发布的话题数量
     */
    private Long discussNum;
    /**
     * 创建时间
     * */
    private Date createTime;
}
