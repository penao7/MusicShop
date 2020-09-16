package oinonen.MusicStore.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MusicStoreDAOimpl implements MusicStoreDAO {
 
RowMapper<Product> pMapper = new ProductRowMapper();
RowMapper<Brand> bMapper = new BrandRowMapper();
RowMapper<Category> cMapper = new CategoryRowMapper();
 
 	@Autowired
 	private JdbcTemplate jdbcTemplate;
 
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
	
		Product product = null;
		
		String sql = "SELECT product_id, b.brand_id, c.category_id, quantity, product_name, model_year, list_price, brand_name, category_name , imgUrl FROM Products p "
			+ "JOIN Brands b ON p.brand_id = b.brand_id "
			+ "JOIN Categories c ON p.category_id = c.category_id "
			+ "WHERE product_id = ?";
		
			Object [] args = new Object[] { id };		
		
			try {
				product = jdbcTemplate.queryForObject(sql, args, pMapper);
				
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
	
 };
};

