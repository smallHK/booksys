package com.hk.service;

import java.util.List;

import com.hk.entity.Books;

public interface ICartService {

	public String addCart(String[] bookIds, String cartId);
	
	//��ѯ���ﳵ������
	public List<Books> findCartInfo(String ids);
}
