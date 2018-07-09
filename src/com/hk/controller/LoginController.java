package com.hk.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	public @ResponseBody JsonBean login(@RequestParam("userName")String username, @RequestParam("passWord")String password, HttpSession session, HttpServletResponse response){
		
		JsonBean bean = new JsonBean();

		try {
			loginService.login(username, password);
			//登录成功，将用户名存放到session对象里
			session.setAttribute("loginname", username);
			String sessionId = session.getId();
			Cookie cookie = new Cookie("JSESSIONID", sessionId);
			cookie.setMaxAge(1800);
			response.addCookie(cookie);
			
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
	
	@RequestMapping("/check")
	public @ResponseBody JsonBean checkUser(String userName){
		
		JsonBean bean = new JsonBean();
		try {
			boolean ret = loginService.userIsExist(userName);
			if(ret == true){
				//不可用
				bean.setCode(-1);
			}else{
				//可用
				bean.setCode(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
		}
		
		return bean;
	}
	
}
