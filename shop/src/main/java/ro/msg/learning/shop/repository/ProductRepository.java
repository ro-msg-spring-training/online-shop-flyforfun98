package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(int id);
    List<Product> findAll();
    Product save(Product product);
    void remove(Product product);
}
