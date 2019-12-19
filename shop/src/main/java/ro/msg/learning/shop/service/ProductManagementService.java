package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.repository.RepositoryFactory;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductManagementService {

    private final RepositoryFactory repositoryFactory;

    @Transactional
    public ProductDTO addProduct(ProductDTO productDTO){

        String name = productDTO.getName();
        String description = productDTO.getDescription();
        BigDecimal price = productDTO.getPrice();
        Double weight = productDTO.getWeight();
        ProductCategory category = repositoryFactory.productCategoryRepository().findById(productDTO.getCategoryId()).orElse(null);
        Supplier supplier = repositoryFactory.supplierRepository().findById(productDTO.getSupplierId()).orElse(null);
        String imageUrl = productDTO.getImageUrl();

        Product product = new Product(name, description, price, weight, category, supplier, imageUrl);
        return ProductDTO.ofEntity(repositoryFactory.productRepository().save(product));
    }

    @Transactional
    public void removeProduct(Integer productId){
        Product product = repositoryFactory.productRepository().findById(productId).orElse(null);
        repositoryFactory.productRepository().remove(product);
    }

    @Transactional
    public List<ProductDTO> getProducts(){
        return repositoryFactory.productRepository().findAll()
                     .stream().map(ProductDTO::ofEntity).collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO getProductById(Integer id){
        return ProductDTO.ofEntity(Objects.requireNonNull(repositoryFactory.productRepository().findById(id).orElse(null)));
    }

    @Transactional
    public void updateProduct(int productId, ProductDTO productDTO){

        Product product = new Product();
        ProductCategory category = repositoryFactory.productCategoryRepository().findById(productDTO.getCategoryId()).orElse(null);
        Supplier supplier = repositoryFactory.supplierRepository().findById(productDTO.getSupplierId()).orElse(null);

        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImageUrl());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setSupplier(supplier);
        product.setWeight(productDTO.getWeight());
        product.setId(productId);
        product.setCategory(category);

        repositoryFactory.productRepository().save(product);
    }
}
