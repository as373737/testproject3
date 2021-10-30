package com.web.cart.dao.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.cart.dao.CartDao;
import com.web.cart.model.Cart;
import com.web.cart.model.Product;
import com.web.cart.model.RecordBean;
import com.web.cart.model.RidCount;



@Repository
public class CartDaoImpl implements CartDao {

	SessionFactory factory;

	@Autowired
	public CartDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public void addItemByid(int pid) {
		System.out.println("pid = " + pid);
		Session session = factory.getCurrentSession();
		Product pb = session.get(Product.class, pid);

		Cart cb = new Cart(pid, pb.getP_img(), pb.getP_name(), pb.getP_price(), 1,"asd123",pb.getSeller());
//		System.out.println("******************************");
		session.save(cb);

		System.out.println(pb.getPid() + "," + pb.getP_name() + "," + pb.getP_img() + "," + pb.getP_price());

	}

	@Override
	public boolean existsById(int pid) {
		Session session = factory.getCurrentSession();
		Query<Cart> query = session.createQuery("from Cart where pid=:pid", Cart.class);
		query.setParameter("pid", pid);
		Cart resultBean = query.uniqueResult();
		if (resultBean != null) {
			return true;
		}
		return false;
	}

	@Override
	public void add(int pid) {
		Session session = factory.getCurrentSession();
		Cart cb = session.get(Cart.class, pid);
		System.out.println("PIDDD = " + cb.getPid() + ",NAMEEEE = " + cb.getCount());
		String hql = "UPDATE Cart c SET c.count =:count WHERE c.pid =:pid";
		session.createQuery(hql).setParameter("count", cb.getCount() + 1)
		.setParameter("pid", pid)
		.executeUpdate();

	}

	@Override
	public void sub(int pid) {
		Session session = factory.getCurrentSession();
		Cart cb = session.get(Cart.class, pid);

		if (cb.getCount() > 1) {
			
			String hql = "UPDATE Cart c SET c.count =:count WHERE c.pid =:pid";
			session.createQuery(hql).setParameter("count", cb.getCount() - 1).setParameter("pid", pid).executeUpdate();

		}
		
	}

	@Override
	public void deletecart(int pid) {
		Session session = factory.getCurrentSession();
		Cart cb = new Cart();
		System.out.println("rid3 = " + pid);
		cb.setPid(pid);
		session.delete(cb);

	}

	@Override
	public void addToRecord(RecordBean rb) {
		Session session = factory.getCurrentSession();

		session.save(rb);
		
	}
	
	@Override
	public void addRidCount(int id) {
		
		Session session = factory.getCurrentSession();
		RidCount rc = session.get(RidCount.class, id);
		System.out.println("11111111111111111111111111"+rc.getRidcount());
//		System.out.println("PIDDD = " + cb.getPid() + ",NAMEEEE = " + cb.getCount());
		String hql = "UPDATE RidCount  SET Ridcount =:Ridcount WHERE id =:id";
	
		session.createQuery(hql).setParameter("Ridcount", rc.getRidcount() + 1)
		.setParameter("id", id)
		.executeUpdate();
		System.out.println("//////////////////////"+rc.getRidcount());
		
	}

	@Override
	public void deleteAll() {

		factory.getCurrentSession().createQuery("delete from Cart").executeUpdate();
	}



}
