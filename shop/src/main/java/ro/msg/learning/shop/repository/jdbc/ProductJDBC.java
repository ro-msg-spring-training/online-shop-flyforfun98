package ro.msg.learning.shop.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductJDBC implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Product> findById(int id) {
        List<Product> products = jdbcTemplate.query("SELECT * FROM product WHERE id = ?",new ProductMapper(), id);
        if(products.isEmpty())
            return Optional.empty();
        else
            return Optional.of(products.get(0));
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product", new ProductMapper());
    }

    @Override
    public Product save(Product product) {
        if(product.getId() == null){
            product.setId(insert(product));
        }
        else{
            update(product);
        }
        return product;
    }

    private int insert(Product product) {

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.setTableName("product");
        insert.setGeneratedKeyName("id");
        Map<String,Object> map = new HashMap<>();
        map.put("name", product.getName());
        map.put("description", product.getDescription());
        map.put("price", product.getPrice());
        map.put("supplier_id", product.getSupplier().getId());
        map.put("weight", product.getWeight());
        map.put("category_id", product.getCategory().getId());
        map.put("image_url", product.getImageUrl());

        return  insert.executeAndReturnKey(map).intValue();
    }

    @Override
    public void remove(Product product) {
        jdbcTemplate.update("DELETE FROM product WHERE id = ?", product.getId());
    }

    private void update(Product product){
        jdbcTemplate.update("UPDATE product SET name = ?, description = ?, price = ?, weight = ?, image_url = ?," +
                                 " supplier_id = ?, category_id = ? WHERE id = ?",
                product.getName(), product.getDescription(), product.getPrice(), product.getWeight(),
                product.getImageUrl(), product.getSupplier().getId(), product.getCategory().getId(), product.getId());
    }
}
