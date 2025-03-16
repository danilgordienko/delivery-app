package ru.danilgordienko.delivery_app.service;


import org.springframework.stereotype.Service;
import ru.danilgordienko.delivery_app.model.Delivery;
import ru.danilgordienko.delivery_app.model.DeliveryReport;
import ru.danilgordienko.delivery_app.model.Provider;
import ru.danilgordienko.delivery_app.repository.DeliveryRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    // Метод для приема поставки
    public Delivery acceptDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    // Метод для формирования отчета
    public Map<Provider, DeliveryReport> getDeliveriesReportBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Delivery> deliveries = deliveryRepository.findByDeliveryDateBetween(startDate, endDate);

        Map<Provider, DeliveryReport> deliveriesByProvider = new HashMap<>();

        for (Delivery delivery : deliveries) {
            Provider provider = delivery.getProvider();
            deliveriesByProvider.putIfAbsent(provider, new DeliveryReport());
            deliveriesByProvider.get(provider).addDelivery(delivery);
        }

        return deliveriesByProvider;
    }
}