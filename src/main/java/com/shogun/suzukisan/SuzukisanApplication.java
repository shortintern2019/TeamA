package com.shogun.suzukisan;

import com.shogun.suzukisan.entity.User;
import com.shogun.suzukisan.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SuzukisanApplication {

    private static final Logger log = LoggerFactory.getLogger(SuzukisanApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SuzukisanApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new User("user", "password"));

            // fetch all customers
            log.info("Customers found with findAll():");
            for (User user : repository.findAll()) {
                log.info(user.toString());
            }
        };
    }

}
