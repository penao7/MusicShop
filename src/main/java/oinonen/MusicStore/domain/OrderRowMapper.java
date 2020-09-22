package oinonen.MusicStore.domain;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper<Order>{
 
@Override
  public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
    Order order = new Order();
    
    order.setDate(rs.getTimestamp("order_date"));
    order.setId(rs.getLong("order_id"));
    order.setTotal_price(rs.getLong("total_price"));

    return order;
	};
};