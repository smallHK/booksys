package com.hk.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.dao.IBookDao;
import com.hk.entity.Books;
import com.hk.service.ICartService;
import com.hk.util.StringUtil;

@Service
public class CartService implements ICartService{

	@Autowired
	private IBookDao bookDao;
	
	
	@Override
	public String addCart(String[] bookIds, String cartId) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		if(!StringUtil.isNullOrEmpty(cartId)){
			String[] split = cartId.split(",");
			for(String v : split){
				list.add(v);
			}
			
		}
		
		if(bookIds == null || bookIds.length == 0){
			throw new RuntimeException("没有选择相关的图书");
		}
		
		// 1,2,3
		String info = "";
		for(int i = 0; i < bookIds.length; i++){
			if(!list.contains(bookIds[i])){
				list.add(bookIds[i]);
			}
		}
		
		for(String v : list){
			info += v + ",";
		}
		info = info.substring(0, info.length() - 1);
		
		return info;
	}

	@Override
	public List<Books> findCartInfo(String ids) {
		// TODO Auto-generated method stub
		
		if(StringUtil.isNullOrEmpty(ids)){
			throw new RuntimeException("购物车中无数据");
		}
		
		try {
			List<String> list = new ArrayList<>();
			String[] split = ids.split(",");
			for(String info:split){
				list.add(info);
			}
			List<Books> books = bookDao.findByIds(list);
			return books;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}

}
