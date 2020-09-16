package oinonen.MusicStore.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper implements RowMapper<Product>{
 
   @Override
  public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
    Product product = new Product();
    product.setId(rs.getLong("product_id"));
    product.setName(rs.getString("product_name"));
    product.setBrand(rs.getString("brand_name"));
    product.setModel_year(rs.getLong("model_year"));
    product.setCategory(rs.getString("category_name"));
    product.setList_price(rs.getLong("list_price"));
    product.setQuantity(rs.getLong("quantity"));
    product.setImgUrl(rs.getString("imgUrl"));

   return product;
   };
 };

