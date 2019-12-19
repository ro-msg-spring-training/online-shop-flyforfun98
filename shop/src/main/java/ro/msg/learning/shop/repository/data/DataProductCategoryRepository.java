package ro.msg.learning.shop.repository.data;

import org.springframework.data.repository.Repository;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

public interface DataProductCategoryRepository extends Repository<ProductCategory, Integer>, ProductCategoryRepository {

    void delete(ProductCategory productCategory);

    @Override
    default void remove(ProductCategory productCategory){
        delete(productCategory);
    }
}
