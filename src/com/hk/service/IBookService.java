package com.hk.service;



import com.hk.entity.Books;
import com.hk.vo.PageBean;

public interface IBookService {

	
	//根据页码进行分页查询
	public PageBean<Books> findByPage(Integer page);
	
	
	
}
