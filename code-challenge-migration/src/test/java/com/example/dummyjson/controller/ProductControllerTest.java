package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureWebTestClient
public class ProductControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setUp() {
        Mockito.reset(productService);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(1L, "Product 1", "Description 1", 10.0);
        Product product2 = new Product(2L, "Product 2", "Description 2", 20.0);
        List<Product> products = List.of(product1, product2);

        when(productService.getAllProducts()).thenReturn(Mono.just(products));

        webTestClient.get()
                .uri("/api/products")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .hasSize(2)
                .consumeWith(response -> {
                    List<Product> result = response.getResponseBody();
                    assertEquals(2, result.size());
                    assertEquals("Product 1", result.get(0).getTitle());
                });

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Product 1", "Description 1", 10.0);
        when(productService.getProductById(1L)).thenReturn(Mono.just(product));

        webTestClient.get()
                .uri("/api/products/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .consumeWith(response -> {
                    Product result = response.getResponseBody();
                    assertEquals("Product 1", result.getTitle());
                });

        verify(productService, times(1)).getProductById(1L);
    }
}