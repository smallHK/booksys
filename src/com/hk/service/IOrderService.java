package com.hk.service;

import com.hk.entity.OrderItem;
import com.hk.entity.Orders;
import com.hk.vo.PageBean;

public interface IOrderService {

	//��Ӷ�����Ϣ
	public Orders addOrderInfo(Double price, String username);
	
	//��Ӷ�����ϸ
	public void addOrderItems(String[] ids, String[] nums, Orders orders);
	
	//����ҳ���ѯ������ϸ��Ϣ(��ҳʱ�����ն��������ݷ�ҳ)
	public PageBean<OrderItem> findItemByIndex(String name, int page);
	
}
