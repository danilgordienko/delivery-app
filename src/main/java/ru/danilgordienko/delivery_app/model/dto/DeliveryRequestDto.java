package ru.danilgordienko.delivery_app.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DeliveryRequestDto {
    private Long providerId;
    private LocalDate deliveryDate;
    private List<DeliveryProductDto> products;
}
