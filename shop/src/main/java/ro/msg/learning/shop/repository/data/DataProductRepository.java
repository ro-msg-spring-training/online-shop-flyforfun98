package ro.msg.learning.shop.repository.data;

import org.springframework.data.repository.Repository;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.repository.ProductRepository;

public interface DataProductRepository extends Repository<Product, Integer>, ProductRepository {

    void delete(Product product);

    @Override
    default void remove(Product product){
        delete(product);
    }
}
