package com.web.cart.dao;

import com.web.cart.model.RecordBean;

public interface CartDao {
	public void addItemByid(int pid);
	public boolean existsById(int pid);
	public void add(int pid);
	public void sub(int pid);
	public void deletecart(int pid);
	public void addToRecord(RecordBean rb);
	public void addRidCount(int id);
	public void deleteAll();
}