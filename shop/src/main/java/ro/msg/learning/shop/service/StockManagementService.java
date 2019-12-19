package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StockManagementService {

    private final RepositoryFactory repositoryFactory;

    public List<Stock> getStocksByLocationId(int locationId){

        return repositoryFactory.stockRepository().findAllByStockIdLocationId(locationId);
    }
}
