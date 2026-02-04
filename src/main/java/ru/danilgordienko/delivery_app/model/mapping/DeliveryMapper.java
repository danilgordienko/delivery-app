package ru.danilgordienko.delivery_app.model.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.danilgordienko.delivery_app.model.dto.DeliveryProductViewDto;
import ru.danilgordienko.delivery_app.model.dto.DeliveryRequestDto;
import ru.danilgordienko.delivery_app.model.dto.DeliveryResponseDto;
import ru.danilgordienko.delivery_app.model.entity.Delivery;
import ru.danilgordienko.delivery_app.model.entity.DeliveryProduct;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface DeliveryMapper {

    @Mapping(source = "product", target = "product")
    DeliveryProductViewDto toViewDto(DeliveryProduct deliveryProduct);

    DeliveryResponseDto toDto(Delivery delivery);

}
