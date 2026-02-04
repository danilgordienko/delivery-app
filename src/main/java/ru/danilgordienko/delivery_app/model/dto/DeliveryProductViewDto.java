package ru.danilgordienko.delivery_app.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeliveryProductViewDto {
    private ProductDto product;
    private Double weight;
}

