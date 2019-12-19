package ro.msg.learning.shop.repository.data;

import org.springframework.data.repository.Repository;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.repository.OrderDetailRepository;

public interface DataOrderDetailRepository extends Repository<OrderDetail, Integer>, OrderDetailRepository {

    void delete(OrderDetail orderDetail);

    @Override
    default void remove(OrderDetail orderDetail){
        delete(orderDetail);
    }
}
