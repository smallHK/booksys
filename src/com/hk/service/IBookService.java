package com.hk.service;



import com.hk.entity.Books;
import com.hk.vo.PageBean;

public interface IBookService {

	
	//����ҳ����з�ҳ��ѯ
	public PageBean<Books> findByPage(Integer page);
	
	
	
}
