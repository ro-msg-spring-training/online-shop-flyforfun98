package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.Revenue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RevenueRepository {
    
    Optional<Revenue> findById(int id);
    List<Revenue> findAll();
    Revenue save(Revenue revenue);
    void remove(Revenue revenue);

    List<Revenue> findAllByDate(LocalDate date);
}
