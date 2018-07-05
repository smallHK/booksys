package com.hk.dao;

import com.hk.base.IBaseDao;
import com.hk.entity.User;

public interface IUserDao extends IBaseDao<User> {
	
	
	//根据用户名查询用户信息
	public User findByName(String name);
	
	
	
}
