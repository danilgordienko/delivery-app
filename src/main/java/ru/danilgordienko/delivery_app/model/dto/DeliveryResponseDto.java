package ru.danilgordienko.delivery_app.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DeliveryResponseDto {
    private Long id;
    private ProviderDto provider;
    private LocalDate deliveryDate;
    private List<DeliveryProductViewDto> products;
}
