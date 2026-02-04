package ru.danilgordienko.delivery_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.danilgordienko.delivery_app.model.dto.ProductDto;
import ru.danilgordienko.delivery_app.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Продукты", description = "API для работы с продуктами")
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Получить список продуктов",
            description = "Возвращает список всех доступных продуктов"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список продуктов успешно получен")
    })
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        log.info("[GET /api/products] Request to get all products");

        var products = productService.getAllProducts();

        log.info("[GET /api/products] Get {} products", products.size());

        return ResponseEntity.ok(products);
    }

    @Operation(
            summary = "Получить продукт по ID",
            description = "Возвращает информацию о продукте по его идентификатору"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт найден"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(
            @Parameter(description = "ID продукта", example = "1")
            @PathVariable Long id) {

        log.info("[GET /api/products/{id}] Request to get product with id {}", id);

        var product = productService.getProductDtoById(id);

        log.info("[GET /api/products/{id}] Successfully get product with id {}", id);

        return ResponseEntity.ok(product);
    }
}


