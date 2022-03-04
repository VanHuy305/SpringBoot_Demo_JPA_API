package com.hit.testspringboot;

import com.hit.testspringboot.entity.Category;
import com.hit.testspringboot.entity.Product;
import com.hit.testspringboot.repository.CategoryRepository;
import com.hit.testspringboot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TestSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringBootApplication.class, args);
    }

}
