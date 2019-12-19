package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.service.StockManagementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockManagementService stockService;

    @GetMapping(value = "/stocks/export/{locationId}",
                produces = "text/csv")
    public List<Stock> getStocksByLocationId(@PathVariable int locationId){
        return stockService.getStocksByLocationId(locationId);
    }
}
