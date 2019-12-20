package ro.msg.learning.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.msg.learning.shop.repository.RepositoryFactory;
import ro.msg.learning.shop.seed.MainSeed;

@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
@EnableWebSecurity
public class ShopApplication {

	private final RepositoryFactory repositoryFactory;
	private final PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initializeDatabase() {
		return new MainSeed(repositoryFactory, passwordEncoder);
	}

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}