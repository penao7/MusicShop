package oinonen.MusicStore;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import oinonen.MusicStore.domain.Brand;
import oinonen.MusicStore.domain.MusicStoreDAOimpl;
import oinonen.MusicStore.domain.Product; 
import oinonen.MusicStore.domain.Category;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SqlTest {
 
	MusicStoreDAOimpl dao = new MusicStoreDAOimpl();
 
 	@Autowired
 	DataSource dataSource;
 	
 	@Test
 	public void addingProductReturnsTrueAndRightProduct() throws SQLException {
		dao.setDataSource(dataSource);
		Product product = new Product();
  	 product.setName("Testi1");
  	 product.setModel_year(new Long(1972));
  	 product.setList_price(new Long(4500));
  	 product.setQuantity(new Long(5));
  	 product.setBrand("Fender");
  	 product.setCategory("Electric Guitar");
  	 product.setImgUrl("images/Testi.jpg");
  	 
  	 assertTrue(dao.addProduct(product));
  	 assertEquals(product.getName(), dao.getProductByName("Testi1").getName());
  	 
  	 dao.deleteProduct(dao.getProductByName("Testi1").getId());
 	};
 	
	@Test
	public void thereAreCorrectInitialAmountOfBrandsAndCategories() throws SQLException {
		dao.setDataSource(dataSource);
		List<Brand> brands = dao.getBrands();
		List<Category> categories = dao.getCategories();
		assertEquals(5, brands.size());
		assertEquals(2, categories.size());		 
	};
		
	@Test
	public void deletingMethodWorks() throws SQLException {
	 	dao.setDataSource(dataSource);
  	 Product product = new Product();
    	 product.setName("Testi5");
    	 product.setModel_year(new Long(1972));
    	 product.setList_price(new Long(4500));
    	 product.setQuantity(new Long(5));
    	 product.setBrand("Fender");
    	 product.setCategory("Electric Guitar");
    	 product.setImgUrl("images/Testi.jpg");
    	 
    	 	assertTrue(dao.addProduct(product));
    	 
    	 	int count = dao.getCountOfProducts();	
    	 	Long id = dao.getProductByName("Testi5").getId();
    	 	dao.deleteProduct(id);
    	 	
    	 	int countAfterDelete = dao.getCountOfProducts();

    	 	assertEquals(countAfterDelete, count - 1);	
	};
	
	@Test
	public void updatingProduct() throws SQLException {
		dao.setDataSource(dataSource);
		Product product = new Product();
  	 product.setName("Testi2");
  	 product.setModel_year(new Long(1972));
  	 product.setList_price(new Long(4500));
  	 product.setQuantity(new Long(5));
  	 product.setBrand("Fender");
  	 product.setCategory("Electric Guitar");
  	 product.setImgUrl("images/Testi.jpg");
  	 
  	 assertTrue(dao.addProduct(product));
  	 Long id = dao.getProductByName("Testi2").getId();
  	 
  	 product.setName("updatedName");
  	 product.setBrand("Aria");
  	 
  	 dao.updateProduct(id, product);
  	 
  	 assertEquals("updatedName", dao.getProductById(id).getName());
  	 assertEquals("Aria", dao.getProductById(id).getBrand());
  	 dao.deleteProduct(id);
	};
	
	@Test
	public void handleOrderReducesStorageValue() throws SQLException {
	 dao.setDataSource(dataSource);
  	 Product product = new Product();
  	 product.setName("Testi3");
  	 product.setModel_year(new Long(1972));
  	 product.setList_price(new Long(4500));
  	 product.setQuantity(new Long(5));
  	 product.setBrand("Fender");
  	 product.setCategory("Electric Guitar");
  	 product.setImgUrl("images/Testi.jpg");
  	 
  	 assertTrue(dao.addProduct(product));
  	 Long id = dao.getProductByName("Testi3").getId();
  	 
  	 dao.handleOrder(id, 5);
  	 
  	 assertEquals(new Long(0), dao.getProductById(id).getQuantity());
  	 dao.deleteProduct(id);
	};
	
	

};


