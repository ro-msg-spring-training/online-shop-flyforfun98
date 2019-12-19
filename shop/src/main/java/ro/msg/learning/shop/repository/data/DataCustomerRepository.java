package ro.msg.learning.shop.repository.data;

import org.springframework.data.repository.Repository;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

public interface DataCustomerRepository extends Repository<Customer, Integer>, CustomerRepository {

    void delete(Customer customer);

    @Override
    default void remove(Customer customer){
        delete(customer);
    }
}
