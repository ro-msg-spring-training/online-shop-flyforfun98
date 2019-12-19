package ro.msg.learning.shop.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.math.BigDecimal;


@Component
@RequiredArgsConstructor
public class ProductSeed {

    public ProductSeed(RepositoryFactory repositoryFactory) {

        ProductRepository productRepository = repositoryFactory.productRepository();
        if(productRepository.findAll().isEmpty())
        {
            ProductCategory productCategory = repositoryFactory.productCategoryRepository().findById(1).orElse(null);
            Supplier supplier = repositoryFactory.supplierRepository().findById(1).orElse(null);

            productRepository.save(new Product("Laptop", "Best Gaming Laptop", BigDecimal.valueOf(99.99), 2.2, productCategory, supplier, ""));
            productRepository.save(new Product("PC", "Best Gaming PC",  BigDecimal.valueOf(199.99), 5.5, productCategory, supplier, ""));
            productRepository.save(new Product("Knife", "Good knife, cuts throats",  BigDecimal.valueOf(10.99), 0.12, productCategory, supplier, ""));
            productRepository.save(new Product("Gun", "Excellent at shooting kids",  BigDecimal.valueOf(87.99), 1.8, productCategory, supplier, ""));
            productRepository.save(new Product("Washing Machine", "Better than a wife",  BigDecimal.valueOf(700.00), 50.1, productCategory, supplier, ""));
        }
    }
}
