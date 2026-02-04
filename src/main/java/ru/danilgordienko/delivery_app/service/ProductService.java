package ru.danilgordienko.delivery_app.service;

import ru.danilgordienko.delivery_app.model.dto.ProductDto;
import ru.danilgordienko.delivery_app.model.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    Product getProductById(Long id);
    ProductDto getProductDtoById(Long id);
}
