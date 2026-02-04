package ru.danilgordienko.delivery_app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.danilgordienko.delivery_app.exceptions.ProductNotFoundException;
import ru.danilgordienko.delivery_app.model.dto.ProductDto;
import ru.danilgordienko.delivery_app.model.entity.Product;
import ru.danilgordienko.delivery_app.model.mapping.ProductMapper;
import ru.danilgordienko.delivery_app.repository.ProductRepository;
import ru.danilgordienko.delivery_app.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getAllProducts_shouldReturnDtoList() {

        List<Product> products =
                List.of(new Product(1L,"Apple", new BigDecimal("100")));

        List<ProductDto> dtos =
                List.of(new ProductDto(1L,"Apple", new BigDecimal("100")));

        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.toProductListDto(products)).thenReturn(dtos);

        List<ProductDto> result = productService.getAllProducts();

        assertEquals(1, result.size());
        verify(productRepository).findAll();
        verify(productMapper).toProductListDto(products);
    }

    @Test
    void getProductById_shouldReturnProduct() {

        Product product =
                new Product(1L,"Apple", new BigDecimal("100"));

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertEquals(product, result);
    }

    @Test
    void getProductById_shouldThrowException() {

        when(productRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productService.getProductById(1L)
        );
    }

    @Test
    void getProductDtoById_shouldReturnDto() {

        Product product =
                new Product(1L,"Apple", new BigDecimal("100"));

        ProductDto dto =
                new ProductDto(1L,"Apple", new BigDecimal("100"));

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(productMapper.toDto(product))
                .thenReturn(dto);

        ProductDto result =
                productService.getProductDtoById(1L);

        assertEquals(dto,result);
    }
}
