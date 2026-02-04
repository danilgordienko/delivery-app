package ru.danilgordienko.delivery_app.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.danilgordienko.delivery_app.exceptions.ProductNotFoundException;
import ru.danilgordienko.delivery_app.model.dto.ProductDto;
import ru.danilgordienko.delivery_app.model.entity.Product;
import ru.danilgordienko.delivery_app.model.mapping.ProductMapper;
import ru.danilgordienko.delivery_app.repository.ProductRepository;
import ru.danilgordienko.delivery_app.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    // получение всех продуктов
    public List<ProductDto> getAllProducts() {
        var products = productRepository.findAll();
        return productMapper.toProductListDto(products);
    }

    // получение продукта по id
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    // получение ProductDto по id
    public ProductDto getProductDtoById(Long id) {
        return productMapper.toDto(getProductById(id));
    }
}
