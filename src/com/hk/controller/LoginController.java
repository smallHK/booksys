package com.hk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.entity.User;
import com.hk.service.ILoginService;
import com.hk.vo.JsonBean;


@Controller
public class LoginController {

	@Autowired
	private ILoginService loginService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody JsonBean login(@RequestParam("userName")String username, @RequestParam("passWord")String password){
		
		JsonBean bean = new JsonBean();

		try {
			loginService.login(username, password);
			bean.setCode(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public @ResponseBody JsonBean register(User user){
		JsonBean bean = new JsonBean();
		
		try {
			loginService.register(user);
			bean.setCode(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
	
}
