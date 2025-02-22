package com.example.dummyjson.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testProductGettersAndSetters() {
        Product product = new Product(1L, "Product 1", "Description 1", 10.0);

        assertEquals(1L, product.getId());
        assertEquals("Product 1", product.getTitle());
        assertEquals("Description 1", product.getDescription());
        assertEquals(10.0, product.getPrice());
    }
}