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
			throw new RuntimeException("用户名为空");
		}
		if(StringUtil.isNullOrEmpty(password)){
			throw new RuntimeException("密码为空");
		}
		
		
		User user;
		try {
			user = userDao.findByName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		if(user == null){
			throw new RuntimeException("用户名不存在");
		}else{
			if(!user.getPassword().equals(password)){
				throw new RuntimeException("密码错误");
			}
		}
		
	}

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		
		if(user == null){
			throw new RuntimeException("用户信息不能为空");
		}
		
		
		if(!user.getPassword().equals(user.getRePassWord())){
			throw new RuntimeException("两次密码不一致");
		}
		
		try {
			userDao.add(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
	}

	public boolean userIsExist(String name){
		
		try {
			User user = userDao.findByName(name);
			if(user == null){
				return false;
			}else{
				return true;
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
	}
	
}
