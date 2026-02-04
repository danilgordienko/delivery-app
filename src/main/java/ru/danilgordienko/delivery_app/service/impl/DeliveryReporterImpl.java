package ru.danilgordienko.delivery_app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.danilgordienko.delivery_app.model.dto.DeliveryProductViewDto;
import ru.danilgordienko.delivery_app.model.dto.DeliveryProviderReportDto;
import ru.danilgordienko.delivery_app.model.dto.DeliveryReportDto;
import ru.danilgordienko.delivery_app.model.dto.ProductDto;
import ru.danilgordienko.delivery_app.model.entity.Delivery;
import ru.danilgordienko.delivery_app.model.entity.DeliveryProduct;
import ru.danilgordienko.delivery_app.model.entity.Product;
import ru.danilgordienko.delivery_app.model.entity.Provider;
import ru.danilgordienko.delivery_app.model.mapping.ProductMapper;
import ru.danilgordienko.delivery_app.service.DeliveryReporter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryReporterImpl implements DeliveryReporter {

    private final ProductMapper productMapper;

    // получение полного отчета
    @Override
    public DeliveryReportDto makeReport(List<Delivery> deliveries) {

        Map<Provider, List<Delivery>> deliveriesByProvider =
                deliveries.stream()
                        .collect(Collectors.groupingBy(Delivery::getProvider));

        Map<String, DeliveryProviderReportDto> report = new HashMap<>();

        for (Map.Entry<Provider, List<Delivery>> entry : deliveriesByProvider.entrySet()) {

            Provider provider = entry.getKey();
            List<Delivery> providerDeliveries = entry.getValue();

            DeliveryProviderReportDto providerReport =
                    makeProviderReport(providerDeliveries);

            report.put(provider.getName(), providerReport);
        }

        DeliveryReportDto dto = new DeliveryReportDto();
        dto.setReport(report);

        return dto;
    }

    // получение отчета по отдельному поставщику
    private DeliveryProviderReportDto makeProviderReport(List<Delivery> deliveries) {

        double totalWeight = 0.0;
        BigDecimal totalCost = BigDecimal.ZERO;
        List<DeliveryProductViewDto> products = new ArrayList<>();

        for (Delivery delivery : deliveries) {

            for (DeliveryProduct dp : delivery.getProducts()) {

                double weight = dp.getWeight();
                Product product = dp.getProduct();

                totalWeight += weight;

                BigDecimal cost =
                        product.getPrice()
                                .multiply(BigDecimal.valueOf(weight));

                totalCost = totalCost.add(cost);

                ProductDto productDto =
                        productMapper.toDto(product);

                var prod = new DeliveryProductViewDto();
                prod.setProduct(productDto);
                prod.setWeight(weight);

                products.add(prod);
            }
        }

        DeliveryProviderReportDto dto =
                new DeliveryProviderReportDto();

        dto.setTotalWeight(totalWeight);
        dto.setTotalCost(totalCost);
        dto.setProducts(products);

        return dto;
    }
}

