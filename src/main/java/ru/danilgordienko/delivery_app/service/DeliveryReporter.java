package ru.danilgordienko.delivery_app.service;

import ru.danilgordienko.delivery_app.model.dto.DeliveryReportDto;
import ru.danilgordienko.delivery_app.model.entity.Delivery;

import java.util.List;

public interface DeliveryReporter {

    DeliveryReportDto makeReport(List<Delivery> deliveries);
}
