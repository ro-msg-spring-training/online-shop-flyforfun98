package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findById(int id);
    List<Customer> findAll();
    Customer save(Customer customer);
    void remove(Customer customer);

    Optional<Customer> findCustomerByUsername(String username);
}
