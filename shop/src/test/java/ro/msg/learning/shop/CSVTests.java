package ro.msg.learning.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.msg.learning.shop.converter.ConverterUtil;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.entity.StockId;
import ro.msg.learning.shop.repository.LocationRepository;

import java.io.*;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes={ConverterUtil.class})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
class CSVTests {

    private final String stockExportFile = "C:\\Users\\moldof\\Desktop\\Spring Training\\online-shop-flyforfun98\\stockExport.txt";
    private final String exportResult = "\"[StockDTO(stockId=StockIdDTO(locationId=1, productId=1), quantity=25), StockDTO(stockId=StockIdDTO(locationId=1, productId=2), quantity=20)]\",";
    private StockDTO stockDTO1, stockDTO2;

    @MockBean
    private LocationRepository locationRepository;

    @BeforeEach
    void loadData() {

        Location location1 = new Location("Location 1","Romania","Cluj - Napoca", "Cluj",
                "Baritiu nr. 9",null,null);
        location1.setId(1);
        Mockito.when(locationRepository.findById(1)).thenReturn(Optional.of(location1));
        Mockito.when(locationRepository.findAll()).thenReturn(Collections.singletonList(location1));

        StockId stockId = new StockId(1, 1);
        Stock stock1 = new Stock(stockId, 25);
        stockDTO1 = StockDTO.ofEntity(stock1);

        stockId = new StockId(1, 2);
        Stock stock2 = new Stock(stockId, 20);
        stockDTO2 = StockDTO.ofEntity(stock2);

    }

    @Test
    void serializeCSV() throws IOException {

        OutputStream outputStream = new FileOutputStream(stockExportFile);
        ConverterUtil converterUtil = new ConverterUtil();
        List<StockDTO> stockDTOList = new ArrayList<>(Arrays.asList(stockDTO1, stockDTO2));
        converterUtil.toCsv(StockDTO.class, stockDTOList, outputStream);

        File file = new File(stockExportFile);
        BufferedReader br = new BufferedReader(new FileReader(file));

        assertThat(br.readLine()).isEqualTo(exportResult);
    }

    @Test
    void deserializeCSV() throws IOException {

        List<StockDTO> stockDTOS = new ArrayList<>(Arrays.asList(stockDTO1, stockDTO2));
        InputStream inputstream = new FileInputStream(stockExportFile);
        ConverterUtil converterUtil = new ConverterUtil();
        List<StockDTO> stocks = converterUtil.fromCsv(StockDTO.class, inputstream);
        assertThat(stockDTOS).isNotEqualTo(stocks);
    }
}
