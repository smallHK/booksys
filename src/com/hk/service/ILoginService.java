package com.hk.service;

import com.hk.entity.User;

public interface ILoginService {

	//��¼
	public void login(String name, String password);
	
	//ע��
	public void register(User user);
	
	
}
