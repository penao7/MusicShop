package oinonen.MusicStore.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class CategoryRowMapper implements RowMapper<Category> {
  
   @Override
   public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
     Category category = new Category();
     category.setId(rs.getLong("category_id"));
     category.setCategory(rs.getString("category_name"));

    return category;
    };
};

