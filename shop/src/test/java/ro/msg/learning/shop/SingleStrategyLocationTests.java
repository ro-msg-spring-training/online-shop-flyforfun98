package ro.msg.learning.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.msg.learning.shop.dto.OrderProductQuantityDTO;
import ro.msg.learning.shop.dto.ProductAndQuantityDTO;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.entity.StockId;
import ro.msg.learning.shop.model.LocationStrategyModel;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes={SingleLocationStrategy.class})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
class SingleStrategyLocationTests {

    @Autowired
    private SingleLocationStrategy singleLocationStrategy;

    @MockBean
    private LocationRepository locationRepository;

    @MockBean
    private StockRepository stockRepository;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void loadData() {

        Location location1 = new Location("Location 1","Romania","Cluj - Napoca", "Cluj",
                "Baritiu nr. 9",null,null);
        location1.setId(1);
        Mockito.when(locationRepository.findById(1)).thenReturn(Optional.of(location1));

        Location location2 = new Location("Location 2","Romania","Targu Mures", "Mures",
                "Muncii nr. 12",null,null);
        location2.setId(2);
        Mockito.when(locationRepository.findById(2)).thenReturn(Optional.of(location2));

        Location location3 = new Location("Location 3","Romania","Bucuresti", "Bucuresti",
                "Primaverii nr 79",null,null);
        location3.setId(3);
        Mockito.when(locationRepository.findById(3)).thenReturn(Optional.of(location3));
        Mockito.when(locationRepository.findAll()).thenReturn(Arrays.asList(location1, location2, location3));

        StockId stockId = new StockId(1, 1);
        Stock stock1 = new Stock(stockId, 25);
        Mockito.when(stockRepository.findById(1)).thenReturn(Optional.of(stock1));

        stockId = new StockId(2, 1);
        Stock stock2 = new Stock(stockId, 16);
        Mockito.when(stockRepository.findById(2)).thenReturn(Optional.of(stock2));

        stockId = new StockId(1, 2);
        Stock stock3 = new Stock(stockId, 20);
        Mockito.when(stockRepository.findById(3)).thenReturn(Optional.of(stock3));

        stockId = new StockId(2, 2);
        Stock stock4 = new Stock(stockId, 21);
        Mockito.when(stockRepository.findById(4)).thenReturn(Optional.of(stock4));
        Mockito.when(stockRepository.findAll()).thenReturn(Arrays.asList(stock1, stock2, stock3, stock4));

        Product product1 = new Product("Laptop", "Best Gaming Laptop", BigDecimal.valueOf(99.99), 2.2, null, null, "");
        product1.setId(1);
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product1));

        Product product2 = new Product("PC", "Best Gaming PC",  BigDecimal.valueOf(199.99), 5.5, null, null, "");
        product2.setId(2);
        Mockito.when(productRepository.findById(2)).thenReturn(Optional.of(product2));
        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
    }

    @Test
    void locationsExist() {

        Location locationTest1 = locationRepository.findById(1).orElse(null);
        assertThat(locationTest1).isNotNull();
        assertThat(locationTest1.getName()).isEqualTo("Location 1");

        Location locationTest2 = locationRepository.findById(2).orElse(null);
        assertThat(locationTest2).isNotNull();
        assertThat(locationTest2.getOrders()).isNull();
        assertThat(locationTest2.getAddressCountry()).isEqualTo("Romania");
        assertThat(locationTest2.getAddressCity()).isEqualTo("Targu Mures");

        Location locationTest3 = locationRepository.findById(3).orElse(null);
        assertThat(locationTest3).isNotNull();
        assertThat(locationTest3.getRevenues()).isNull();
        assertThat(locationTest3.getAddressCounty()).isEqualTo("Bucuresti");
        assertThat(locationTest3.getName()).isEqualTo("Location 3");
    }

    @Test
    void stocksExist(){

        Stock stockTest1 = stockRepository.findById(1).orElse(null);
        assertThat(stockTest1).isNotNull();
        assertThat(stockTest1.getQuantity()).isEqualTo(25);
        assertThat(stockTest1.getStockId().getLocationId()).isEqualTo(1);
        assertThat(stockTest1.getStockId().getProductId()).isEqualTo(1);

        Stock stockTest2 = stockRepository.findById(2).orElse(null);
        assertThat(stockTest2).isNotNull();
        assertThat(stockTest2.getQuantity()).isGreaterThan(15);
        assertThat(stockTest2.getQuantity()).isLessThan(17);
        assertThat(stockTest2.getStockId().getLocationId()).isEqualTo(2);

        Stock stockTest3 = stockRepository.findById(5).orElse(null);
        assertThat(stockTest3).isNull();
    }

    @Test
    void getSingleLocationListWithRequiredStock() {

        List<ProductAndQuantityDTO> productsList = new ArrayList<>();

        OrderProductQuantityDTO orderProduct = new OrderProductQuantityDTO();
        orderProduct.setCustomerId(1);
        orderProduct.setAddressStreetAddress("Baritiu nr. 9");
        orderProduct.setAddressCounty("Cluj");
        orderProduct.setAddressCity("Cluj - Napoca");
        orderProduct.setAddressCountry("Romania");
        orderProduct.setCreatedAt("2019-12-16 16:30:00");

        ProductAndQuantityDTO productQuantity1 = new ProductAndQuantityDTO();
        productQuantity1.setProductId(1);
        productQuantity1.setProductQuantity(10);
        productsList.add(productQuantity1);

        ProductAndQuantityDTO productQuantity2 = new ProductAndQuantityDTO();
        productQuantity2.setProductId(2);
        productQuantity2.setProductQuantity(15);
        productsList.add(productQuantity2);

        orderProduct.setProductAndQuantityDTOList(productsList);

        Location location1 = locationRepository.findById(1).orElse(null);
        Product product1 = productRepository.findById(1).orElse(null);
        Product product2 = productRepository.findById(2).orElse(null);

        List<LocationStrategyModel> locationStrategyModelList = new ArrayList<>();
        locationStrategyModelList.add(new LocationStrategyModel(location1, product1, productQuantity1.getProductQuantity()));
        locationStrategyModelList.add(new LocationStrategyModel(location1, product2, productQuantity2.getProductQuantity()));

        assertThat(singleLocationStrategy.getLocations(orderProduct)).isEqualTo(locationStrategyModelList);

    }
}
