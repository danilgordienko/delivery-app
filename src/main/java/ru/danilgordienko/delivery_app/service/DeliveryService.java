package ru.danilgordienko.delivery_app.service;

import ru.danilgordienko.delivery_app.model.dto.DeliveryReportDto;
import ru.danilgordienko.delivery_app.model.dto.DeliveryRequestDto;

import java.time.LocalDate;

public interface DeliveryService {
    void acceptDelivery(DeliveryRequestDto dto);
    DeliveryReportDto getDeliveriesReportBetweenDates(LocalDate startDate, LocalDate endDate);

}
