package ro.msg.learning.shop.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.msg.learning.shop.entity.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryMapper implements RowMapper<ProductCategory> {

    @Override
    public ProductCategory mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ProductCategory(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description")
        );
    }
}
