package ru.danilgordienko.delivery_app.service;


import org.springframework.stereotype.Service;
import ru.danilgordienko.delivery_app.model.Product;
import ru.danilgordienko.delivery_app.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
