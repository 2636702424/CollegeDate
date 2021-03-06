package com.dhrs.date.common.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * 
 * @author ly
 * @email 986352486@qq.com
 * @date 2020-07-24 14:20:58
 */
@Data
@TableName("user_member_tag")
public class MemberTagEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long memberId;
	/**
	 * 
	 */
	private Long tagId;
	/**
	 * 
	 */
	@TableId
	private Long id;

}
