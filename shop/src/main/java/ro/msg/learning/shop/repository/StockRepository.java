package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.Stock;

import java.util.List;
import java.util.Optional;

public interface StockRepository {

    Optional<Stock> findById(int id);
    List<Stock> findAll();
    Stock save(Stock stock);
    void remove(Stock stock);

    List<Stock> findAllByStockIdLocationId(int locationId);
}
