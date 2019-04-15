package com.radek.rentals;

import com.radek.rentals.entity.Role;
import com.radek.rentals.entity.User;
import com.radek.rentals.repository.RoleRepository;
import com.radek.rentals.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperties.class)
public class RentalsApplication {


    public static void main(String[] args) {
        SpringApplication.run(RentalsApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository,
                                 BCryptPasswordEncoder bCryptPasswordEncoder) { //to co jest w tej metodzie wykona się po starcie aplikacji w springu
        return args -> {
            List<Role> roles = roleRepository.findAll();

            List<User> users = userRepository.findAll();
            users.stream().forEach(u -> {
                u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));

                Collections.shuffle(roles);
                u.getRoles().addAll(roles.subList(0, ThreadLocalRandom.current().nextInt(1, 3)));
            });

            userRepository.saveAll(users);

        };
    }

}
