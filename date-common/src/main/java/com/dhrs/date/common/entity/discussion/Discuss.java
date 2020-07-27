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
 * @since 2020-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("clg_discuss")
public class Discuss implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 话题id
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 标题
     */
    private String headline;

    /**
     * 具体内容
     */
    private String content;

    /**
     * 点赞数量
     */
    private Long likes;

    /**
     * 回复数量
     */
    private Long reply;

    /**
     * 浏览数量
     */
    private Long browse;

    /**
     * 兴趣圈id(0表示在讨论区发表话题)
     */
    private String interestId;

    /**
     * 用户id
     */
    private Long uid;

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
