package com.coolcuy.service;

import java.util.List;

public interface TransGenericService<T>{
	public int add(T object);
	public int delete(int element);
	public int deleteAll();
	public T update(T object);
	public T get(int element);
	public List<T> getAll();
}
