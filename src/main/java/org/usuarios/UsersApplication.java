package org.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("org.usuarios.model")
public class UsersApplication {
    public static void main(String[] args) {

        SpringApplication.run(UsersApplication.class, args);
    }


}

