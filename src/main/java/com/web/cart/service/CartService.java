package com.web.cart.service;

import java.util.List;

import com.web.cart.model.Cart;
import com.web.cart.model.RecordBean;


public interface CartService {
	public void addItemByid(int pid);
	public boolean existsById(int pid);
	public void add(int pid);
	public void sub(int pid);
	public void deletecart(int pid);
	public  List<Cart> addToRecord();
	public void addToRecord2(RecordBean rb);
	public int getRidCount(int id);
	public void addRidCount();
	public void deleteAll();
	
}