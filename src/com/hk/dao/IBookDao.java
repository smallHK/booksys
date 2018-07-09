package com.hk.dao;

import java.util.List;
import java.util.Map;

import com.hk.base.IBaseDao;
import com.hk.entity.Books;

public interface IBookDao extends IBaseDao<Books>{
	
	//���з�ҳ��ѯ�����ĸ�������ʼ��ÿҳ��ʾ������¼
	public List<Books> findByIndex(Map<String, Object> pageInfo);
	
	//��������鼮id���в�ѯ
	public List<Books> findByIds(List<String> ids);
	
	
}
