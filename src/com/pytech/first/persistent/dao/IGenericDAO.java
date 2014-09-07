package com.pytech.first.persistent.dao;

import java.util.List;
import java.util.Map;

public interface IGenericDAO<T> {
	public void insert(T record);
	
	public void delete(int id);
	
	public void update(T record);
	
	public T findById(int id);
	
	public List<T> findByColumn(String colName, Object value);
	
	public List<T> findByColumns(Map<String, Object> colMap);
	
	public List<T> findInColumn(String colName, Object[] values);
	
	public List<T> findInColumns(Map<String, Object[]> colMap);
	
	public List<T> findByColumnAndInColumn(Map<String, Object> byColMap, Map<String, Object[]> inColMap);
}
