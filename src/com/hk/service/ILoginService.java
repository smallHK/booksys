package com.hk.service;

import com.hk.entity.User;

public interface ILoginService {

	//µÇÂ¼
	public void login(String name, String password);
	
	//×¢²á
	public void register(User user);
	
	
}
