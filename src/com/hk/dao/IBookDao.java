package com.hk.dao;

import java.util.List;
import java.util.Map;

import com.hk.base.IBaseDao;
import com.hk.entity.Books;

public interface IBookDao extends IBaseDao<Books>{
	
	//进行分页查询，从哪个索引开始，每页显示几条记录
	public List<Books> findByIndex(Map<String, Object> pageInfo);
	
	//根据相关书籍id进行查询
	public List<Books> findByIds(List<String> ids);
	
	
}
