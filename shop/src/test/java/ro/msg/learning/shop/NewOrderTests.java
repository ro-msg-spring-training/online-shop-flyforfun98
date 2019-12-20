package ro.msg.learning.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;
import ro.msg.learning.shop.dto.OrderProductQuantityDTO;
import ro.msg.learning.shop.dto.ProductAndQuantityDTO;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.repository.data.*;
import ro.msg.learning.shop.repository.jdbc.ProductCategoryJDBC;
import ro.msg.learning.shop.repository.jdbc.ProductJDBC;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ShopApplication.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@Profile(value = "test")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
class NewOrderTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DataSupplierRepository supplierRepository;
    @MockBean
    private ProductJDBC productRepository;
    @MockBean
    private DataOrderRepository orderRepository;
    @MockBean
    private ProductCategoryJDBC productCategoryRepository;
    @MockBean
    private DataCustomerRepository customerRepository;
    @MockBean
    private DataStockRepository stockRepository;
    @MockBean
    private DataLocationRepository locationRepository;
    @MockBean
    private DataRevenueRepository revenueRepository;
    @MockBean
    private DataOrderDetailRepository orderDetailRepository;


    @BeforeEach
    void loadData() {

        Location location1 = new Location("Location 1", "Romania", "Cluj - Napoca", "Cluj",
                "Baritiu nr. 9", null, null);
        location1.setId(1);
        Location location2 = new Location("Location 2", "Romania", "Targu Mures", "Mures",
                "Muncii nr. 12", null, null);
        location2.setId(2);
        Mockito.when(locationRepository.findById(1)).thenReturn(Optional.of(location1));
        Mockito.when(locationRepository.findById(2)).thenReturn(Optional.of(location2));
        Mockito.when(locationRepository.findAll()).thenReturn(Arrays.asList(location1, location2));

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

        Product product1 = new Product("Laptop", "Best Gaming Laptop", BigDecimal.valueOf(99.99), 2.2, null, null, "");
        product1.setId(1);
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product1));
        Mockito.when(stockRepository.findAll()).thenReturn(Arrays.asList(stock1, stock2, stock3, stock4));

        Product product2 = new Product("PC", "Best Gaming PC", BigDecimal.valueOf(199.99), 5.5, null, null, "");
        product2.setId(2);
        Mockito.when(productRepository.findById(2)).thenReturn(Optional.of(product2));
        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        Customer customer = new Customer("Moldovan", "Flavius", "12345678", "password", "flavy@kys.com", null);
        customer.setId(1);
        Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2019-12-16 16:30:00", formatter);

        Order order = new Order(location1, customer, dateTime, "Romania", "Cluj - Napoca",
                "Cluj", "Baritiu nr. 9");
        Mockito.when(orderRepository.save(order)).thenReturn(order);
    }

    @Test
    void createSuccessfulOrder() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/orders")

                .content(Objects.requireNonNull(convertToJSON(createOrder(10, 15))))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].createdAt").value("2019-12-16 16:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addressCountry").value("Romania"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addressCity").value("Cluj - Napoca"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addressCounty").value("Cluj"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].addressStreetAddress").value("Baritiu nr. 9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].customerId").value(1))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].createdAt").value("2019-12-16 16:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addressCountry").value("Romania"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addressCity").value("Cluj - Napoca"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addressCounty").value("Cluj"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].addressStreetAddress").value("Baritiu nr. 9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].customerId").value(1));

    }


    @Test()
    void createUnsuccessfulOrder() {

        Assertions.assertThrows(NestedServletException.class, () ->

                mvc.perform(MockMvcRequestBuilders.post("/orders")
                    .content(Objects.requireNonNull(convertToJSON(createOrder(9, 22))))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError()));
    }


    private static String convertToJSON(OrderProductQuantityDTO orderProduct){

        ObjectMapper Obj = new ObjectMapper();

        try {
            return Obj.writeValueAsString(orderProduct);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static OrderProductQuantityDTO createOrder(Integer quantity1, Integer quantity2){
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
        productQuantity1.setProductQuantity(quantity1);
        productsList.add(productQuantity1);

        ProductAndQuantityDTO productQuantity2 = new ProductAndQuantityDTO();
        productQuantity2.setProductId(2);
        productQuantity2.setProductQuantity(quantity2);
        productsList.add(productQuantity2);
        orderProduct.setId(1);

        orderProduct.setProductAndQuantityDTOList(productsList);

        return orderProduct;
    }
}
