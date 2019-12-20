package ro.msg.learning.shop.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductCategoryJDBC implements ProductCategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<ProductCategory> findById(int id) {

        List<ProductCategory> productCategories = jdbcTemplate.query("SELECT * FROM product_category WHERE id = ?", new ProductCategoryMapper(), id);
        if(productCategories.isEmpty())
            return Optional.empty();
        else
            return Optional.of(productCategories.get(0));
    }

    @Override
    public List<ProductCategory> findAll() {
        return jdbcTemplate.query("SELECT * FROM product_category", new ProductCategoryMapper());
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        if(productCategory.getId() == null) {
            productCategory.setId(insert(productCategory));
        }
        else{
            update(productCategory);
        }
        return productCategory;
    }

    @Override
    public void remove(ProductCategory productCategory) {
        jdbcTemplate.update("DELETE FROM product_category WHERE id = ?", productCategory.getId());
    }

    private int insert(ProductCategory productCategory){

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.setTableName("product_category");
        insert.usingGeneratedKeyColumns("id");
        Map<String,Object> map = new HashMap<>();
        map.put("name", productCategory.getName());
        map.put("description", productCategory.getDescription());

        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(ProductCategory productCategory)
    {
        jdbcTemplate.update("UPDATE product_category SET name = ?, description = ? WHERE id = ?",
                productCategory.getName(), productCategory.getDescription(), productCategory.getId());
    }
}
