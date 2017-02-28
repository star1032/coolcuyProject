package com.coolcuy.service;

import java.util.List;

public interface GenericService<T> {
	
	public int add(T object);
	public int delete(String element);
	public int deleteAll();
	public T update(T object);
	public T get(String element);
	public List<T> getAll();
	
}
