package com.web.cart.controller.cart_30;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.cart.model.Cart;
import com.web.cart.model.RecordBean;
import com.web.cart.model.RidCount;
import com.web.cart.service.CartService;




@Controller
public class CartController {
	CartService cartService;

	@Autowired
	public CartController(CartService cartService) {

		this.cartService = cartService;
	}



	@GetMapping({"/test"})
	public String home(Model model) {
		 return "cart/TotalHome";
	}
	
	
	
	@GetMapping("/additem")
	public String additem(@RequestParam Integer id ,Model model) {
		System.out.println("PID = "+id);
		boolean exists = cartService.existsById(id);
		System.out.println("exists = "+exists);
		if(exists!=true) {
			cartService.addItemByid(id);		
		}
		else {
			cartService.add(id);	
		}
		model.addAttribute("additem");
		return "cart/TotalHome";
	}
	
	
	
	@GetMapping("/cart")
	public String cart(Model model) {
		
		
		 return "cart/cart";
	}
	
	
	@GetMapping("/deletecart")
	public String deletecart(@RequestParam Integer id ,Model model) {
		cartService.deletecart(id);	
		 return "cart/cart";
	}
	
	@GetMapping("/add")
	public String add(@RequestParam Integer id,Model model) {
		System.out.println("starttttt===============");
		cartService.add(id);	
		System.out.println("===============endddddddddddd");
		 return "cart/cart";
	}
	
	@GetMapping("/sub")
	public String sub(@RequestParam Integer id,Model model) {
		System.out.println("starttttt===============");
		cartService.sub(id);	
		System.out.println("===============endddddddddddd");
		 return "cart/cart";
	}
	
	@GetMapping("/check")
	public String check(Model model) {
		 return "cart/check";
	}
	
	
	@GetMapping("/fin")
	public String fin(Model model) {
		List<Cart> cart = cartService.addToRecord();
		int rc = cartService.getRidCount(1);
		RecordBean rb =new RecordBean();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String now = dtf.format(LocalDateTime.now());
		System.out.println(now);
		for(Cart c:cart) {
			rb.setRecord_id(rc);
			rb.setPid(c.getPid());
			rb.setP_name(c.getP_name());
			rb.setP_price(c.getP_price());
			rb.setPcount(c.getCount());
			rb.setBuyer("asd1234");
			rb.setSeller(c.getSeller());
			rb.setBuy_time(now);
			System.out.println("RID = "+rb.getRecord_id()+", PID = "+rb.getPid()+", NAME = "
					+ rb.getP_name()+", PRICE = "+rb.getP_price()+", CNT = "+rb.getPcount()
					+", BUYER = "+rb.getBuyer()+", SELLER = "+rb.getSeller());
			
			cartService.addToRecord2(rb);

		}
		cartService.addRidCount();
		 return "cart/fin";
	}
	
	@GetMapping("/removeAllCart")
	public String removeAllCart(Model model) {
		cartService.deleteAll();
		 return "cart/TotalHome";
	}
	
	
	
	
	
	
}
