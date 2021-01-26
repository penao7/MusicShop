package oinonen.MusicStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class MusicStoreApplication implements CommandLineRunner {
 
 private static final Logger log = LoggerFactory.getLogger(MusicStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MusicStoreApplication.class, args);
	}
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
	 
	 log.info("Setting up intitial data to database");
	 
	 jdbcTemplate.execute("INSERT INTO Users "
	 	+ "(username, password, role, enabled) VALUES "
	 	+ "('admin', '$2y$12$0e9HDxRl5W9gI/fNmEFSnepBU.56yqM4pBhJhfoSIfVZXxzat30wi', 'ROLE_ADMIN', 1), "
	 	+ "('user', '$2y$12$J9gP0IFKgY8PJA3aGqaNHuwB7K8cwabjf791q6z.k/Tl1BVSKy3Au', 'ROLE_USER', 1);"
	 	);
	 
	 jdbcTemplate.execute("INSERT INTO Customers "
	 	+ "(customer_id, first_name, last_name, user_id, street, city, post_code) VALUES "
	 	+ "(customer_id, 'Admin', 'Test', 1, 'TestAddress', 'Example', 00000), "
	 	+ "(customer_id, 'User', 'Test', 2, 'Testroad 23', 'Testvillage', 62626);"
	 	);	 
	 
	 jdbcTemplate.execute("INSERT INTO Brands " +
	 "(brand_id, brand_name) VALUES " +
	 "(brand_id, 'Fender'), " +
	 "(brand_id, 'Aria'), " +
	 "(brand_id, 'Charvel'), " +
	 "(brand_id, 'Epiphone'), " +
	 "(brand_id, 'Gibson');"
	 );
	 
	 jdbcTemplate.execute("INSERT INTO Categories " +
	 "(category_id, category_name) VALUES " +
	 "(category_id, 'Electric Guitar'), " +
	 "(category_id, 'Electric Bass');"
	 );
	 
	 jdbcTemplate.execute("INSERT INTO Products " +
	 "(product_id, product_name, model_year, list_price, brand_id, category_id, quantity, imgUrl) VALUES " +
	 "(product_id, 'Model 1820', 1972, 4500, 2, 2, 5, 'images/ARIA.jpg'), " +
	 "(product_id, 'Jazz Bass', 1964, 6750, 1, 2, 7, 'images/FENDER1967.jpg'), " +
	 "(product_id, 'Precision Bass', 1966, 8500, 2, 2, 2, 'images/PRECISION.jpg'), " +
	 "(product_id, 'USA So-Cal', 2016, 6000, 3, 1, 1, 'images/CHARVEL.jpg'), " +
	 "(product_id, 'Tony Iommi SG', 2015, 7500, 4, 1, 4, 'images/IOMMI.jpg'), " +
	 "(product_id, 'EB-0', 1982, 2050, 5, 1, 1, 'images/GIBSON1982.jpg');"
	 );	 
	 	
	}
}
