package ro.msg.learning.shop.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.repository.*;

@Component
@RequiredArgsConstructor
@Repository
public class DataRepositoryFactory implements RepositoryFactory {

    private final DataSupplierRepository supplierRepository;
    private final DataProductCategoryRepository productCategoryRepository;
    private final DataOrderRepository orderRepository;
    private final DataCustomerRepository customerRepository;
    private final DataLocationRepository locationRepository;
    private final DataRevenueRepository revenueRepository;
    private final DataProductRepository productRepository;
    private final DataStockRepository stockRepository;
    private final DataOrderDetailRepository orderDetailRepository;


    @Override
    public SupplierRepository supplierRepository() {
        return supplierRepository;
    }

    @Override
    public ProductCategoryRepository productCategoryRepository() {
        return productCategoryRepository;
    }

    @Override
    public ProductRepository productRepository() {
        return productRepository;
    }

    @Override
    public CustomerRepository customerRepository() {
        return customerRepository;
    }

    @Override
    public LocationRepository locationRepository() {
        return locationRepository;
    }

    @Override
    public OrderRepository orderRepository() {
        return orderRepository;
    }

    @Override
    public RevenueRepository revenueRepository() {
        return revenueRepository;
    }

    @Override
    public StockRepository stockRepository() {
        return stockRepository;
    }

    @Override
    public OrderDetailRepository orderDetailRepository() {
        return orderDetailRepository;
    }
}
