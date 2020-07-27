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
@TableName("clg_reply")
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 回复id
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 讨论话题id
     */
    private String commentId;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 父级回复id(0表示是一级回复)
     */
    private String parentId;

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
