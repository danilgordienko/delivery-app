package ru.danilgordienko.delivery_app.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.danilgordienko.delivery_app.exceptions.BusinessException;
import ru.danilgordienko.delivery_app.model.dto.DeliveryReportDto;
import ru.danilgordienko.delivery_app.model.dto.DeliveryRequestDto;
import ru.danilgordienko.delivery_app.model.entity.Delivery;
import ru.danilgordienko.delivery_app.model.entity.DeliveryProduct;
import ru.danilgordienko.delivery_app.model.mapping.DeliveryMapper;
import ru.danilgordienko.delivery_app.repository.DeliveryRepository;
import ru.danilgordienko.delivery_app.service.DeliveryReporter;
import ru.danilgordienko.delivery_app.service.DeliveryService;
import ru.danilgordienko.delivery_app.service.ProductService;
import ru.danilgordienko.delivery_app.service.ProviderService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final ProviderService providerService;
    private final ProductService productService;
    private final DeliveryReporter deliveryReporter;

    // Метод для приема поставки
    @Override
    @Transactional
    public void acceptDelivery(DeliveryRequestDto dto) {

        Delivery delivery = new Delivery();
        delivery.setDeliveryDate(dto.getDeliveryDate());
        delivery.setProvider(
                providerService.getProviderById(dto.getProviderId())
        );

        List<DeliveryProduct> products = dto.getProducts().stream()
                .map(p -> {
                    DeliveryProduct dp = new DeliveryProduct();
                    dp.setDelivery(delivery);
                    dp.setProduct(
                            productService.getProductById(p.getProductId())
                    );
                    dp.setWeight(p.getWeight());
                    return dp;
                })
                .toList();

        delivery.setProducts(products);

        deliveryRepository.save(delivery);
    }

    // Метод для формирования отчета
    public DeliveryReportDto getDeliveriesReportBetweenDates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BusinessException("Start date cannot be after end date");
        }

        List<Delivery> deliveries = deliveryRepository.findByDeliveryDateBetween(startDate, endDate);
        return deliveryReporter.makeReport(deliveries);
    }
}