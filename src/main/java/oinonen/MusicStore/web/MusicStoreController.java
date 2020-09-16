package oinonen.MusicStore.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import oinonen.MusicStore.domain.Brand;
import oinonen.MusicStore.domain.Category;
import oinonen.MusicStore.domain.MusicStoreDAO;
import oinonen.MusicStore.domain.Product;

@Controller
public class MusicStoreController {
 
 @Autowired
 private MusicStoreDAO dao;
 
 @GetMapping("productdata")
	public @ResponseBody List<Product> productsData() {
		return (List<Product>) dao.getProducts();
 };
 
 @GetMapping("productdata/{id}")
	public @ResponseBody Product getProductById(@PathVariable("id") Long productId) {
		return dao.getProductById(productId);
 };
 
 @GetMapping("/login")
	public String loginPage() {
	return "login";
};

 @GetMapping("/")
 public String index(Model model) {
	
	return "index";
 }

 @GetMapping("products")
 public String products(Model model) {
	
		List<Product> products = dao.getProducts();
	
		model.addAttribute("products", products);
	
		return "productList";
 };
 
 @GetMapping("products/{prodId}")
 public String showProduct(
	 @PathVariable Long prodId, 
	 Model model
 ) {
	
	Product product = dao.getProductById(prodId);
	
	model.addAttribute("product", product);

	return "product";
 };
 
 @GetMapping("delProd/{prodId}")
 public String deleteProduct(
	 @PathVariable Long prodId,
	 RedirectAttributes redirAttrs
 ) {
		
	if(!dao.deleteProduct(prodId)) {
	 	redirAttrs.addFlashAttribute("error", "Something went wrong");
	 	return "redirect:/products";
	}
	
	redirAttrs.addFlashAttribute("success", "Deletion successful");
	return "redirect:/products";
};
 
 @GetMapping("editProd/{productId}")
 public String editProduct(
	 @PathVariable Long productId,
	 Model model
 ) {
	Product product = dao.getProductById(productId);
	List<Category> categories = dao.getCategories();
	List<Brand> brands = dao.getBrands();
	model.addAttribute("product", product);
	model.addAttribute("brands", brands);
	model.addAttribute("categories", categories);
				
	return "edit";
 };
 
@PostMapping("editProd/{productId}")
  public String editProduct(
  @Valid 
  @ModelAttribute Product product,
  BindingResult bindingResult,
  @PathVariable Long productId, 
	RedirectAttributes redirAttrs,
  Model model)
  {
 
 product.setId(productId);
 model.addAttribute("product", product);

	List<Brand> brands = dao.getBrands();
	List<Category> categories = dao.getCategories();
	
	model.addAttribute("brands", brands);
	model.addAttribute("categories", categories);

 	if(bindingResult.hasErrors()) {
 	 	return "edit";
 	};
 	 
   if(!dao.updateProduct(productId, product)) {
  		redirAttrs.addFlashAttribute("error", "Something went wrong");
  		return "redirect:/products";
   }
   
   redirAttrs.addFlashAttribute("success", "Edited successfully product " + product.getBrand() + ' ' + product.getName());
   return "redirect:/products";

};

@GetMapping("addProduct")
public String addProduct(Model model){
	List<Brand> brands = dao.getBrands();
	List<Category> categories = dao.getCategories();
	model.addAttribute("brands", brands);
	model.addAttribute("categories", categories);
	model.addAttribute("product", new Product());
    return "add";
};

@PostMapping("addProduct")
public String postProduct(
	Model model, 
	@Valid Product product, 
	BindingResult bindingResult,
	RedirectAttributes redirAttrs
	) {
 
  	List<Brand> brands = dao.getBrands();
  	List<Category> categories = dao.getCategories();
  	model.addAttribute("brands", brands);
  	model.addAttribute("categories", categories);
   
    if(bindingResult.hasErrors()) {
   		return "add";
    }
    
    if(!dao.addProduct(product)) {
     	redirAttrs.addFlashAttribute("error", "Something went wrong");
     	return "redirect:/products";
    }
    
    redirAttrs.addFlashAttribute("success", "Successfully added product " + product.getBrand() + ' ' + product.getName());
    return "redirect:/products";
	};
	
};



