package ru.danilgordienko.delivery_app.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class DeliveryProviderReportDto {
    private Double totalWeight;
    private BigDecimal totalCost;
    private List<DeliveryProductViewDto> products;
}

