package com.dhrs.date.common.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author ly
 * @email 986352486@qq.com
 * @date 2020-07-24 14:20:58
 */
@Data
@TableName("user_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private String nickname;
	/**
	 * 
	 */
	private String mobile;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String avatar;
	/**
	 * 
	 */
	private Integer gender;
	/**
	 * 
	 */
	private Date birthday;
	/**
	 * 
	 */
	private String city;
	/**
	 * 
	 */
	private Long schoolId;
	/**
	 * 
	 */
	private String province;
	/**
	 * 
	 */
	private Integer status;

}
