package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository {

    Optional<OrderDetail> findById(int id);
    List<OrderDetail> findAll();
    OrderDetail save(OrderDetail orderDetail);
    void remove(OrderDetail orderDetail);

    List<OrderDetail> findAllByOrderDetailIdOrderId(int orderId);
}
