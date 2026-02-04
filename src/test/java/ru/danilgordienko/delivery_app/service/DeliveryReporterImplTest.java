package ru.danilgordienko.delivery_app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.danilgordienko.delivery_app.model.dto.DeliveryProviderReportDto;
import ru.danilgordienko.delivery_app.model.dto.DeliveryReportDto;
import ru.danilgordienko.delivery_app.model.dto.ProductDto;
import ru.danilgordienko.delivery_app.model.entity.Delivery;
import ru.danilgordienko.delivery_app.model.entity.DeliveryProduct;
import ru.danilgordienko.delivery_app.model.entity.Product;
import ru.danilgordienko.delivery_app.model.entity.Provider;
import ru.danilgordienko.delivery_app.model.mapping.ProductMapper;
import ru.danilgordienko.delivery_app.service.impl.DeliveryReporterImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryReporterImplTest {

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private DeliveryReporterImpl deliveryReporter;

    @Test
    void makeReport_shouldAggregateDeliveriesByProvider() {

        Provider provider = new Provider(1L, "Provider1");

        Product product = new Product(1L,"Apple",
                new BigDecimal("100"));

        ProductDto productDto =
                new ProductDto(1L,"Apple",
                        new BigDecimal("100"));

        when(productMapper.toDto(product))
                .thenReturn(productDto);

        DeliveryProduct dp = new DeliveryProduct();
        dp.setProduct(product);
        dp.setWeight(2.0);

        Delivery delivery = new Delivery();
        delivery.setProvider(provider);
        delivery.setProducts(List.of(dp));

        DeliveryReportDto report =
                deliveryReporter.makeReport(List.of(delivery));

        assertNotNull(report);
        assertTrue(report.getReport().containsKey("Provider1"));

        DeliveryProviderReportDto providerReport =
                report.getReport().get("Provider1");

        assertEquals(2.0, providerReport.getTotalWeight());
        assertEquals(new BigDecimal("200.0"),
                providerReport.getTotalCost());

        assertEquals(1, providerReport.getProducts().size());
    }
}
