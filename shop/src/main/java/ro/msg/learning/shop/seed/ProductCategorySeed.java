package ro.msg.learning.shop.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;

@Component
@RequiredArgsConstructor
public class ProductCategorySeed {

    public ProductCategorySeed(RepositoryFactory repositoryFactory) {

        ProductCategoryRepository productCategoryRepository = repositoryFactory.productCategoryRepository();

        if(productCategoryRepository.findAll().isEmpty()){

            productCategoryRepository.save(new ProductCategory("Electronics", "Best electronics", null));
            productCategoryRepository.save(new ProductCategory("Kitchen","Best kitchen things", null));
        }
    }
}
