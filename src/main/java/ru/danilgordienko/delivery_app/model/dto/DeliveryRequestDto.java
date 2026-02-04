package ru.danilgordienko.delivery_app.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DeliveryRequestDto {
    private Long providerId;
    @NotNull
    private LocalDate deliveryDate;
    @Size(min = 1, message = "count of products must be more than 0")
    private List<DeliveryProductDto> products;
}
