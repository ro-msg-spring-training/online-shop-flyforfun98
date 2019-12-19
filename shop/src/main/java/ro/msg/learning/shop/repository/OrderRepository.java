package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findById(int id);
    List<Order> findAll();
    Order save(Order order);
    void remove(Order order);

    List<Order> findAllByShippedFrom(Location location);
}
