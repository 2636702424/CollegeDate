package com.dhrs.date.common.entity.discussion;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zxq
 * @since 2020-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clg_interest_circle")
public class InterestCircle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 兴趣圈id
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 圈名称
     */
    private String circleName;

    /**
     * 兴趣圈图标
     */
    private String avatar;

    /**
     * 关注该圈的用户数量
     */
    private Long fansNum;

    /**
     * 该圈发布的话题数量
     */
    private Long discussNum;

    /**
     * 兴趣圈简介
     */
    private String comment;

    /**
     * 创建兴趣圈的用户id
     */
    private Long uid;

    /**
     * 兴趣圈类型id
     */
    private String sortId;


    /**
     * 发布时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
