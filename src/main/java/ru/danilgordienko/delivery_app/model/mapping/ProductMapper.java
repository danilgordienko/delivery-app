package ru.danilgordienko.delivery_app.model.mapping;

import org.mapstruct.Mapper;
import ru.danilgordienko.delivery_app.model.dto.ProductDto;
import ru.danilgordienko.delivery_app.model.entity.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);

    List<ProductDto> toProductListDto(List<Product> products);
}

