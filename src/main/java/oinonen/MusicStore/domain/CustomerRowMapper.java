package oinonen.MusicStore.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer>{
   
   @Override
   public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
     Customer customer = new Customer();
     customer.setId(rs.getLong("customer_id"));
     customer.setFirst_name(rs.getString("first_name"));
     customer.setLast_name(rs.getString("last_name"));
     customer.setStreet(rs.getString("street"));
     customer.setCity(rs.getString("city"));
     customer.setPost_code(rs.getInt("post_code"));
     
    return customer;
    };
  };
