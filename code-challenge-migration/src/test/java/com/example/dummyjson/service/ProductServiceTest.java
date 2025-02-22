package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Usando MockitoExtension
public class ProductServiceTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private ProductService productService; // Injetando mocks no ProductService

    private WebClient webClient;

    @BeforeEach
    public void setup() {
        webClient = mock(WebClient.class);
        WebClient.RequestHeadersUriSpec requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Product.class)).thenReturn(Mono.just(new Product(1L, "Product 1", "Description 1", 10.0)));
        when(responseSpec.bodyToFlux(Product.class)).thenReturn(Flux.just(new Product(1L, "Product 1", "Description 1", 10.0)));
    }

    @Test
    public void testGetProductById() {
        Product mockProduct = new Product(1L, "Product 1", "Description 1", 10.0);

        StepVerifier.create(productService.getProductById(1L))
                .expectNext(mockProduct)
                .verifyComplete();
    }

    @Test
    public void testGetAllProducts() {
        List<Product> mockProducts = Collections.singletonList(new Product(1L, "Product 1", "Description 1", 10.0));

        StepVerifier.create(productService.getAllProducts())
                .expectNext(mockProducts)
                .verifyComplete();
    }
}