package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    private final String BASE_URL = "https://dummyjson.com/products";

    @Autowired
    private RestTemplate restTemplate;

    public List<Product> getAllProducts() {
        Product[] products = restTemplate.getForObject(BASE_URL, Product[].class);
        return Arrays.asList(products);
    }

    public Product getProductById(Long id) {
        String url = BASE_URL + "/" + id;
        return restTemplate.getForObject(url, Product.class);
    }
}
