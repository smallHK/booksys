package com.hk.service;

import com.hk.entity.OrderItem;
import com.hk.entity.Orders;
import com.hk.vo.PageBean;

public interface IOrderService {

	//添加订单信息
	public Orders addOrderInfo(Double price, String username);
	
	//添加订单明细
	public void addOrderItems(String[] ids, String[] nums, Orders orders);
	
	//根据页码查询订单明细信息(分页时，按照订单中数据分页)
	public PageBean<OrderItem> findItemByIndex(String name, int page);
	
}
