package ro.msg.learning.shop.repository;

public interface RepositoryFactory {

    SupplierRepository supplierRepository();

    ProductCategoryRepository productCategoryRepository();

    ProductRepository productRepository();

    CustomerRepository customerRepository();

    LocationRepository locationRepository();

    OrderRepository orderRepository();

    RevenueRepository revenueRepository();

    StockRepository stockRepository();

    OrderDetailRepository orderDetailRepository();
}
