package com.jvfl;

import com.jvfl.gestaodecisoes.infrastructure.reposytory.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class GestaoDecisoesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoDecisoesApplication.class, args);
    }
}
