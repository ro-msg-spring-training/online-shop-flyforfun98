package ro.msg.learning.shop.repository.jdbc;


import org.springframework.jdbc.core.RowMapper;
import ro.msg.learning.shop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Product(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getBigDecimal("price"),
                resultSet.getDouble("weight"),
                resultSet.getString("image_url")
        );
    }
}
