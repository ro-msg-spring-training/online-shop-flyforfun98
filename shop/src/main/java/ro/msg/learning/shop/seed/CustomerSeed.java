package ro.msg.learning.shop.seed;

import org.springframework.security.crypto.password.PasswordEncoder;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;

class CustomerSeed {
    
    CustomerSeed(RepositoryFactory repositoryFactory, PasswordEncoder passwordEncoder) {

        CustomerRepository customerRepository = repositoryFactory.customerRepository();

        if(customerRepository.findAll().isEmpty()){

            customerRepository.save(new Customer("Flavius", "Moldovan",
                    "blabla", passwordEncoder.encode("blabla1"), "ceva@yahoo.com", null));
            customerRepository.save(new Customer("Antonio", "Marasescu",
                    "customer1", passwordEncoder.encode("customer1"), "ceva2@yahoo.com", null));
        }
    }
    
}
