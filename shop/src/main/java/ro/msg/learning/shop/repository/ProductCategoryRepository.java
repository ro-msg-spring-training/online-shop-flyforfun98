package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository {

    Optional<ProductCategory> findById(int id);
    List<ProductCategory> findAll();
    ProductCategory save(ProductCategory productCategory);
    void remove(ProductCategory productCategory);
}
