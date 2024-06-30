package com.example.xemtruyen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing()
public class XemTruyenApplication {

    public static void main(String[] args) {
        SpringApplication.run(XemTruyenApplication.class, args);
    }

}
