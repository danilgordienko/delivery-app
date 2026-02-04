package ru.danilgordienko.delivery_app.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeliveryProductDto {
    private Long productId;
    @NotNull
    @Min(value = 0, message = "weight must be more than 0")
    private Double weight;
}
