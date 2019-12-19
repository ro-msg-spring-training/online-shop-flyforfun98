package ro.msg.learning.shop.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.repository.RepositoryFactory;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MainSeed implements CommandLineRunner {

    private final RepositoryFactory repositoryFactory;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) {

        new SupplierSeed(repositoryFactory);
        new ProductCategorySeed(repositoryFactory);
        new ProductSeed(repositoryFactory);
        new LocationSeed(repositoryFactory);
        new StockSeed(repositoryFactory);
        new CustomerSeed(repositoryFactory, passwordEncoder);

    }
}
