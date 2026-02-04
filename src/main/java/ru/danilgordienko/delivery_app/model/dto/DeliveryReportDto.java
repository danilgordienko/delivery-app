package ru.danilgordienko.delivery_app.model.dto;

import lombok.Data;
import ru.danilgordienko.delivery_app.model.entity.Provider;

import java.util.Map;

@Data
public class DeliveryReportDto {

    Map<String, DeliveryProviderReportDto> report;
}
