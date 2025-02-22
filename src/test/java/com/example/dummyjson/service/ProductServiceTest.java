package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testGetProductById() {
        Mono<Product> productMono = productService.getProductById(1L);

        StepVerifier.create(productMono)
                .expectNextMatches(product -> product.getId() == 1L)
                .verifyComplete();
    }

    @Test
    public void testGetAllProducts() {
        Mono<List<Product>> productsMono = productService.getAllProducts();

        StepVerifier.create(productsMono)
                .expectNextMatches(products -> products.size() > 0)
                .verifyComplete();
    }
}