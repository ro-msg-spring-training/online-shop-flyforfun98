package ro.msg.learning.shop.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.entity.StockId;
import ro.msg.learning.shop.repository.RepositoryFactory;
import ro.msg.learning.shop.repository.StockRepository;

@Component
@RequiredArgsConstructor
public class StockSeed {

    public StockSeed(RepositoryFactory repositoryFactory) {

        StockRepository stockRepository = repositoryFactory.stockRepository();

        if(stockRepository.findAll().isEmpty()){

            StockId stockId = new StockId(1, 1);
            stockRepository.save(new Stock(stockId, 25));

            stockId = new StockId(2, 1);
            stockRepository.save(new Stock(stockId, 16));

            stockId = new StockId(1, 2);
            stockRepository.save(new Stock(stockId, 20));

            stockId = new StockId(2, 2);
            stockRepository.save(new Stock(stockId, 21));
        }
    }
}
