package com.hk.dao;

import java.util.List;
import java.util.Map;

import com.hk.base.IBaseDao;
import com.hk.entity.OrderItem;

public interface IOrderItemDao extends IBaseDao<OrderItem>{
	
	
	//��ѯ������ϸ
	public List<OrderItem> findByIndex(Map<String, Object> info); 
	
	
	
}
