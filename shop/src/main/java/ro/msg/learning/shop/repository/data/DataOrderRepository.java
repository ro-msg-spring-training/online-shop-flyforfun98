package ro.msg.learning.shop.repository.data;

import org.springframework.data.repository.Repository;
import ro.msg.learning.shop.entity.Order;
import ro.msg.learning.shop.repository.OrderRepository;

public interface DataOrderRepository extends Repository<Order, Integer>, OrderRepository {

    void delete(Order order);

    @Override
    default void remove(Order order){
        delete(order);
    }
}
