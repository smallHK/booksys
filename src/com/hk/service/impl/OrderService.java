package com.hk.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.dao.IOrderDao;
import com.hk.dao.IOrderItemDao;
import com.hk.dao.IUserDao;
import com.hk.entity.Books;
import com.hk.entity.OrderItem;
import com.hk.entity.Orders;
import com.hk.entity.User;
import com.hk.service.IOrderService;
import com.hk.util.StringUtil;
import com.hk.vo.PageBean;

@Service
public class OrderService implements IOrderService{

	@Autowired
	private IOrderDao orderDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IOrderItemDao itemDao;
	
	@Override
	public Orders addOrderInfo(Double price, String username) {
		// TODO Auto-generated method stub
		
		//添加订单
		
		Orders orders = new Orders();
		orders.setTotalPrice(price);
		orders.setState(0);
		orders.setCreateDate(new Date());
		
		//UUID,全球统一ID，不会重复，作为订单编号
		orders.setOrderNum(UUID.randomUUID().toString());
		
		try {
			User user = userDao.findByName(username);
			orders.setBuyer(user);
			
			orderDao.add(orders);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		
		return orders;
	}

	@Override
	public void addOrderItems(String[] ids, String[] nums, Orders orders) {
		// TODO Auto-generated method stub
		
		if(ids == null || ids.length == 0){
			throw new RuntimeException("图书数据不存在");
		}
		if(nums == null || nums.length == 0){
			throw new RuntimeException("购买数量不存在");
		}
		if(orders == null){
			throw new RuntimeException("订单数据不存在");
		}
		
		try {
			for(int i = 0 ; i < ids.length; i++){
				OrderItem item = new OrderItem();
				Books book = new Books();
				book.setId(Integer.parseInt(ids[i]));
				item.setBook(book);
				item.setOrders(orders);
				item.setNum(Integer.parseInt(nums[i]));
				
				itemDao.add(item);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		
	}

	@Override
	public PageBean<OrderItem> findItemByIndex(String name, int page) {
		// TODO Auto-generated method stub
		
		
		if(StringUtil.isNullOrEmpty(name)){
			throw new RuntimeException("用户名为空，不能查询");
		}
		
		PageBean<OrderItem> pageInfo = new PageBean<>();
		pageInfo.setCurrentPage(page);
		
		try {
			int count = orderDao.count();
			pageInfo.setCount(count);
			
			int totalPage = 0;
			if(count % pageInfo.getSize() == 0){
				totalPage = count / pageInfo.getSize();
			}else{
				totalPage = count / pageInfo.getSize() + 1;
			}
			pageInfo.setTotalPage(totalPage);
			
			Map<String, Object> map = new HashMap<>();
			map.put("index", (page - 1) * pageInfo.getSize());
			map.put("size", pageInfo.getSize());
			map.put("name", name);
			List<OrderItem> items = itemDao.findByIndex(map);
			
			pageInfo.setPageInfos(items);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		
		return pageInfo;
	}

}
