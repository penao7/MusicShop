package oinonen.MusicStore.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BrandRowMapper implements RowMapper<Brand> {
   
    @Override
    public Brand mapRow(ResultSet rs, int rowNum) throws SQLException {
     	Brand brand = new Brand();
     	brand.setId(rs.getLong("brand_id"));
     	brand.setBrand(rs.getString("brand_name"));

     	return brand;
   };
};
