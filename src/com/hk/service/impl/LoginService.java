package com.hk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.dao.IUserDao;
import com.hk.entity.User;
import com.hk.service.ILoginService;
import com.hk.util.StringUtil;


@Service
public class LoginService implements ILoginService{

	
	@Autowired
	private IUserDao userDao;
	
	
	@Override
	public void login(String name, String password) {
		// TODO Auto-generated method stub
		if(StringUtil.isNullOrEmpty(name)){
			throw new RuntimeException("�û���Ϊ��");
		}
		if(StringUtil.isNullOrEmpty(password)){
			throw new RuntimeException("����Ϊ��");
		}
		
		
		User user = userDao.findByName(name);
		if(user == null){
			throw new RuntimeException("�û���������");
		}else{
			if(!user.getPassword().equals(password)){
				throw new RuntimeException("�������");
			}
		}
		
	}

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		
	}

	
	
}
