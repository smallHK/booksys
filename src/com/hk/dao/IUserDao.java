package com.hk.dao;

import com.hk.base.IBaseDao;
import com.hk.entity.User;

public interface IUserDao extends IBaseDao<User> {
	
	
	//�����û�����ѯ�û���Ϣ
	public User findByName(String name);
	
	
	
}
