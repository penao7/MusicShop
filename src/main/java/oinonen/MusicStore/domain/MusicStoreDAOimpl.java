package oinonen.MusicStore.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MusicStoreDAOimpl implements MusicStoreDAO {
 
private static final String SQL_INSERT = null;
RowMapper<Product> pMapper = new ProductRowMapper();
RowMapper<Brand> bMapper = new BrandRowMapper();
RowMapper<Category> cMapper = new CategoryRowMapper();
RowMapper<Customer> customerRowMapper = new CustomerRowMapper();
RowMapper<Order> orderRowMapper = new OrderRowMapper();
 
 	@Autowired
 	private JdbcTemplate jdbcTemplate;
 	private DataSource dataSource;
 
 	public JdbcTemplate getJdbcTemplate() {
 	 	return jdbcTemplate;
 	};
 	
  public void setDataSource(DataSource dataSource) {
   	jdbcTemplate = new JdbcTemplate(dataSource);
  };

 @Override
 public List<Product> getProducts() {
	
	List<Product> products = null;
	try {
		String sql = "SELECT DISTINCT product_id, b.brand_id, c.category_id, quantity, product_name, model_year, list_price, brand_name, category_name, imgUrl FROM Products p "
			+ "JOIN Brands b ON p.brand_id = b.brand_id "
			+ "JOIN Categories c ON p.category_id = c.category_id";

		 products = jdbcTemplate.query(sql, pMapper);
		 return products;
	} 
	catch (DataAccessException exception) {
	 throw exception;
	}
	
 };

 @Override
 public boolean deleteProduct(Long id) {
	
	String sql = "DELETE FROM Products WHERE product_id = ?";
	
	Object [] args = new Object[] { id };
	
	try {
  	int rows = jdbcTemplate.update(sql, args);
  	if(rows > 0) {
   		return true;
  	}
  	return true;
	} catch (DataAccessException exception ){
	 	throw exception;

	}
		
};

 @Override
 public Product getProductById(Long id) {
			
		String sql = "SELECT product_id, b.brand_id, c.category_id, quantity, product_name, model_year, list_price, brand_name, category_name , imgUrl FROM Products p "
			+ "JOIN Brands b ON p.brand_id = b.brand_id "
			+ "JOIN Categories c ON p.category_id = c.category_id "
			+ "WHERE product_id = ?";
		
			Object [] args = new Object[] { id };		
		
			try {
				Product product = jdbcTemplate.queryForObject(sql, args, pMapper);
				
				System.out.println("Product " + product.getName() + " found from database");
				
				return product;
			} catch (DataAccessException exception) {
			 	throw exception;
			}
 };

 @Override
 public List<Category> getCategories() {
	String sql = "SELECT * FROM Categories";
		
	try {
		return jdbcTemplate.query(sql, cMapper);
	} catch (DataAccessException exception) {
	 	throw exception;
	}

};

 @Override
 public List<Brand> getBrands() {
	String sql = "SELECT * FROM Brands";

	try {
		return jdbcTemplate.query(sql, bMapper);
	} catch (DataAccessException exception) {
	 	throw exception;
	}
 };

 @Override
 public boolean updateProduct(Long id, Product product) {

	String sql = "UPDATE Products SET "
		+ "product_name = ?, "
		+ "brand_id = ?, "
		+ "model_year = ?, "
		+ "category_id = ?, "
		+ "list_price = ?, "
		+ "quantity = ? "
		+ "WHERE product_id = ?";
	
	try {
		Brand brand = getBrandByName(product.getBrand());
		Category category = getCategoryByName(product.getCategory());
		
		int rows = 
			jdbcTemplate.update(sql, 
				product.getName(), 
				brand.getId(), 
				product.getModel_year(), 
				category.getId(),
				product.getList_price(), 
				product.getQuantity(), 
				id
		);
		
		if(rows > 0) {
		 return true;
		}
	} catch (DataAccessException exception) {
	 	throw exception;
	}
	return false;
};


 @Override
 public Brand getBrandByName(String query) {
	String sql = "SELECT * FROM Brands WHERE brand_name = ?";
	Object[] args = new Object[] { query };
	Brand brand = jdbcTemplate.queryForObject(sql, args, bMapper);
	
	return brand;
 }

 @Override
 public Category getCategoryByName(String query) {
	String sql = "SELECT * FROM Categories WHERE category_name = ?";
	Object[] args = new Object[] { query };
	Category category = jdbcTemplate.queryForObject(sql, args, cMapper);
	
	return category;
 };
 
 public boolean addProduct(Product product) {
	String sql = "INSERT INTO Products "	  
		 + "(product_id, product_name, model_year, list_price, brand_id, category_id, quantity, imgUrl) VALUES "
		 + "(product_id, ?, ?, ?, ?, ?, ?, ?);";

	try {
	 
		Brand brand = getBrandByName(product.getBrand());
		Category category = getCategoryByName(product.getCategory());
		
		Object[] args = new Object[] { 
			product.getName(),
			product.getModel_year(),
			product.getList_price(),
			brand.getId(),
			category.getId(),
			product.getQuantity(),
			product.getImgUrl()
		 };
		
		int rows = jdbcTemplate.update(sql, args);  
		
		if(rows > 0) {
		 	return true;
		}
	 
	} catch (DataAccessException exception) {
	 	throw exception;
	}
	
	return false;

 };

 @Override
 public boolean handleOrder(Long id, int quantity) {
	String sql = "UPDATE Products SET "
		+ "quantity = ? "
		+ "WHERE product_id = ?";
	
	Product product = this.getProductById(id);
	product.setQuantity(product.getQuantity() - quantity);
	
	Object [] args = new Object[] {
	 	product.getQuantity(),
	 	product.getId()
	};
	
	try {
	 
	 int rows = jdbcTemplate.update(sql, args);
	 
	 if(rows > 0) {
		return true;
	 }
	 
	} catch (DataAccessException exception) {
	 	throw exception;
	}
	return false;
	
 };
 
 public Product getProductByName(String name) {
	String sql = "SELECT * FROM Products p "
		+ "JOIN Brands b ON b.brand_id = p.brand_id "
		+ "JOIN Categories c ON c.category_id = p.category_id "
		+ "WHERE product_name = ?";
	Product product = null;
	try {
  	 Object [] args = new Object[] { name };
  	 
  	 product = jdbcTemplate.queryForObject(sql, args, pMapper);
  	 if(product != null) {
  			return product;
  	 }
	} 
	catch (DataAccessException exception) {
	 throw exception;
	}
	return product;

	}

 @Override
 public int getCountOfProducts() {
	String sql = "SELECT COUNT(*) FROM Products";
	return jdbcTemplate.queryForObject(sql, Integer.class);
 }


 @Override
 public boolean createCustomer(Customer customer) {
	String sql = "INSERT INTO Customers (customer_id, first_name, last_name, street, city, post_code) VALUES "
		+ "(customer_id, ?, ?, ?, ?, ?)";
	
	Object[] args = new Object[] {
		customer.getFirst_name(),
		customer.getLast_name(),
		customer.getStreet(),
		customer.getCity(),
		customer.getPost_code()
	};
	
	try {
	 int rows = jdbcTemplate.update(sql, args);
	 
	 if(rows > 0) {
		return true;
	 }
	 
	} catch(DataAccessException exception) {
	 	throw exception;
	}
	
	return false;
	
 };
	

 @Override
 public Long getCustomerIdByName(String firstName, String lastName) {
	String sql = "SELECT * FROM Customers c WHERE c.first_name = ? AND c.last_name = ?";
	
	
	Object [] args = new Object[] {
		firstName,
		lastName
	};
	
	try {
	 Customer customer = jdbcTemplate.queryForObject(sql, args, customerRowMapper);
	
		return customer.getId();
		
	} catch (DataAccessException exception) {
	 	throw exception;
	}
 };

 @Override
 public Long createOrderAndGetId (Long customerId) throws SQLException {
	
	String sql = "INSERT INTO Orders(order_id, customer_id, order_date) VALUES(order_id, ?, now())";
	
	try (
	Connection connection = jdbcTemplate.getDataSource().getConnection();
		
       PreparedStatement statement = connection.prepareStatement(sql,
                                     Statement.RETURN_GENERATED_KEYS);
   ) {
	 
	 			statement.setLong(1, customerId);
       int affectedRows = statement.executeUpdate();
      

       if (affectedRows == 0) {
           throw new SQLException("Creating user failed, no rows affected.");
       }

       try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
           if (generatedKeys.next()) {
               return generatedKeys.getLong(1);
           }
           else {
               throw new SQLException("Creating user failed, no ID obtained.");
           }
       } catch (DataAccessException exception) {
      		throw exception;
       }
   } catch (DataAccessException exception) {
  	throw exception;
   }
 };

 @Override
 public boolean addProductToOrder(Long orderId, Product product) {
	
	String sql = "INSERT INTO Order_items(order_id, product_id, quantity, list_price) VALUES(?, ?, ?, ?)";
	 
	try {
	 
		Object [] args = new Object[] {
			orderId,
			product.getId(),
			product.getCart(),
			product.getList_price()
		};
		
	 int rows = jdbcTemplate.update(sql, args);
	 if(rows > 0) {
		return true;
	 }
	} catch (DataAccessException exception) {
	 throw exception;
	}
	
	return false;
	
 }

 @Override
 public Customer getCustomerByUserName(String username) {
	String sql = "SELECT * from Customers c JOIN Users u ON c.user_id = u.user_id WHERE username = ?";
	
	Object[] args = new Object[] { username };
	
	try {
	 Customer customer = jdbcTemplate.queryForObject(sql, args, customerRowMapper);
	 return customer;
	}
	catch (DataAccessException exception) {
	 throw exception;
	}
 }

 @Override
 public List<Order> getOrdersByCustomerId(Long id) {
	String sql = "SELECT o.order_id, SUM(oi.list_price) 'total_price', o.order_date as 'order_date' FROM Orders o "
		+ "JOIN Order_items oi ON o.order_id = oi.order_id "
		+ "JOIN Products p ON p.product_id = oi.product_id "
		+ "JOIN Customers c ON c.customer_id = o.customer_id "
		+ "WHERE o.customer_id = ? "
		+ "GROUP BY o.order_id;";
	
	Object [] args = new Object[] { id };
	
	try {
		List<Order> orders = jdbcTemplate.query(sql, args, orderRowMapper);
		return orders;
	} catch (DataAccessException exception) {
	 	throw exception;
	}
	
 }

 @Override
 public Order getOrderById(Long id) {
	Order order = null;
	
	String sql = "SELECT order_id, b.brand_id, c.category_id, quantity, product_name, model_year, list_price, brand_name, category_name , imgUrl FROM Products p "
		+ "JOIN Brands b ON p.brand_id = b.brand_id "
		+ "JOIN Categories c ON p.category_id = c.category_id "
		+ "WHERE product_id = ?";
	
		Object [] args = new Object[] { id };		
	
		try {
		 return order;
		} catch (DataAccessException exception) {
		 	throw exception;
		}
 }
};

