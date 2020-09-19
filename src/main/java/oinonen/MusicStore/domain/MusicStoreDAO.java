package oinonen.MusicStore.domain;

import java.sql.SQLException;
import java.util.List;

public interface MusicStoreDAO {
 
 public List<Product> getProducts();
 
 public boolean deleteProduct(Long id);
 
 public Product getProductById(Long id);
 
 public boolean updateProduct(Long id, Product product);
 
 public List<Category> getCategories();
 
 public List<Brand> getBrands();
 
 public Brand getBrandByName(String brand);
 
 public Category getCategoryByName(String category);
 
 public boolean addProduct(Product product);
 
 public boolean handleOrder(Long id, int quantity);
 
 public int getCountOfProducts();
 
 public boolean createCustomer(Customer customer);
 
 public Long getCustomerIdByName(String firstName, String lastName);
 
 public Long createOrderAndGetId(Long customerId) throws SQLException;
 
 public boolean addProductToOrder(Long orderId, Product product);

}
