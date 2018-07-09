package com.hk.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.entity.Books;
import com.hk.service.ICartService;
import com.hk.vo.JsonBean;

@Controller
public class CartController {
	
	@Autowired
	private ICartService cartService;
	
	//���ﳵ��������ݣ���������ݴ�ŵ�cookie
	@RequestMapping(value="/carts", method=RequestMethod.POST)
	//��ȡָ��cookie
	public @ResponseBody JsonBean addCart(@RequestParam("bookId")String[] bookIds,/* @CookieValue("cartids")String cartId,*/ HttpServletResponse response){
		
		
		
		JsonBean bean = new JsonBean();
		
		String cartId = "";
		try {
		
			String info = cartService.addCart(bookIds, cartId);
			
			Cookie cookie = new Cookie("cartids", info);
			cookie.setMaxAge(30 * 24 * 3600);
			response.addCookie(cookie);//cookie
			
			bean.setCode(1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
	/*	Cookie cookie = new Cookie("cartids", info);
		cookie.setMaxAge(30 * 24 * 3600);
		response.addCookie(cookie);//cookie��ӵ����������
*/		

		
		return bean;
	}
	
	@RequestMapping(value="/carts", method=RequestMethod.GET)
	public @ResponseBody JsonBean carts(@CookieValue("cartids") String cartId){
		
		JsonBean bean = new JsonBean();
		
		try {
			List<Books> infos = cartService.findCartInfo(cartId);
			bean.setCode(1);
			bean.setMsg(infos);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
	
}
