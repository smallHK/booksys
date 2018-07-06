package com.hk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.entity.Books;
import com.hk.service.IBookService;
import com.hk.vo.JsonBean;
import com.hk.vo.PageBean;

@Controller
public class BookController {

	@Autowired
	private IBookService bookService;
	
	//http://..../books?page=2
	//以下更适合前后端分离开发的url格式，RESTful API
	@RequestMapping(value="/books/page/{page}", method=RequestMethod.GET)
	//@PathVariable 表示从路径中取对应变量的值
	public @ResponseBody JsonBean findByPage(@PathVariable("page") Integer page){
		
		JsonBean bean = new JsonBean();
		
		try {
			PageBean<Books> infos = bookService.findByPage(page);
			bean.setCode(1);
			bean.setMsg(infos);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
		
	}
	
}
