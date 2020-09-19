package oinonen.MusicStore.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import oinonen.MusicStore.domain.Customer;
import oinonen.MusicStore.domain.MusicStoreDAOimpl;
import oinonen.MusicStore.domain.Product;

@Controller
public class CartController {
 
 	@Autowired
 	MusicStoreDAOimpl dao;
 
 	@GetMapping("/cart")
 		public String cart() {
			return "cart";
	};
	
	@GetMapping("buy/{productId}")
		public String buy(@PathVariable("productId") Long id, HttpSession session) { 	
	 	if (session.getAttribute("cart") == null) {
  	 	 	List<Product> cart = new ArrayList<Product>();
  	 	 	Product product = dao.getProductById(id);
  	 	 	product.setCart(1);
  	 	 	cart.add(product);
  	 	 	session.setAttribute("cart", cart);
	 	} 
	 	else {
	 	 		@SuppressWarnings("unchecked")
				List<Product> cart = (List<Product>) session.getAttribute("cart");
	 	 		int index = this.exists(id, cart);
	 	 		
	 	 		if(index == -1) {
	  	 	 	Product product = dao.getProductById(id);
	  	 	 	product.setCart(1);
	 	 		 	cart.add(product);	
	 	 		} else {
	 	 		 	int quantity = cart.get(index).getCart() + 1;
	 	 		 	cart.get(index).setCart(quantity);
	 	 		}
	 	 		session.setAttribute("cart", cart);
	 	}
	 	return "redirect:/cart/";
};

	@GetMapping("remove/{productId}")
		public String remove(@PathVariable("productId") Long id, HttpSession session) {
  	 	@SuppressWarnings("unchecked")
			List<Product> cart = (List<Product>) session.getAttribute("cart");
  	 	int index = this.exists(id, cart);
  	 	cart.remove(index);
  	 	session.setAttribute("cart", cart);
  	 	return "redirect:/cart";
	};
	
  @GetMapping("order")
	public String customerDetails(Model model) {
   
   model.addAttribute("customer", new Customer());
 	 return "order";
	};
		
	@PostMapping("order")
		public String order(
			@Valid Customer customer, 
			BindingResult bindingResult, 
			HttpSession session, 
			RedirectAttributes redirAttrs
		) throws SQLException {
	 	 
	 	dao.createCustomer(customer);
	 	Long customerId = dao.getCustomerIdByName(customer.getFirst_name(), customer.getLast_name());
	 	Long orderId = dao.createOrderAndGetId(customerId);

 	 	@SuppressWarnings("unchecked")
 		List<Product> cart = (List<Product>) session.getAttribute("cart");
  	
  	cart.forEach(product -> {
  	 dao.handleOrder(product.getId(), product.getCart());
 	 	 dao.addProductToOrder(orderId, product);
  	 
 	 	});
 	 	
 	 	session.removeAttribute("cart");
 	 	redirAttrs.addFlashAttribute("success", "Thank you for you order!");

	 	return "redirect:/cart";
	};

	private int exists(Long id, List<Product> cart) {
	 for(int i = 0; i < cart.size(); i++) {
		if(cart.get(i).getId() == id) {
		 	return i; 
		}
	 }
	 return -1;
	};
	
};
	
 
