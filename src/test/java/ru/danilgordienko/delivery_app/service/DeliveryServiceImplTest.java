package ru.danilgordienko.delivery_app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.danilgordienko.delivery_app.exceptions.BusinessException;
import ru.danilgordienko.delivery_app.model.dto.DeliveryProductDto;
import ru.danilgordienko.delivery_app.model.dto.DeliveryReportDto;
import ru.danilgordienko.delivery_app.model.dto.DeliveryRequestDto;
import ru.danilgordienko.delivery_app.model.entity.Delivery;
import ru.danilgordienko.delivery_app.model.entity.Product;
import ru.danilgordienko.delivery_app.model.entity.Provider;
import ru.danilgordienko.delivery_app.model.mapping.DeliveryMapper;
import ru.danilgordienko.delivery_app.repository.DeliveryRepository;
import ru.danilgordienko.delivery_app.service.impl.DeliveryServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceImplTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private DeliveryMapper deliveryMapper;

    @Mock
    private ProviderService providerService;

    @Mock
    private ProductService productService;

    @Mock
    private DeliveryReporter deliveryReporter;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    @Test
    void acceptDelivery_shouldSaveDelivery() {

        Provider provider =
                new Provider(1L,"Provider1");

        Product product =
                new Product(1L,"Apple",
                        new BigDecimal("100"));

        when(providerService.getProviderById(1L))
                .thenReturn(provider);

        when(productService.getProductById(1L))
                .thenReturn(product);

        DeliveryProductDto prodDto =
                new DeliveryProductDto();

        prodDto.setProductId(1L);
        prodDto.setWeight(5.0);

        DeliveryRequestDto request =
                new DeliveryRequestDto();

        request.setProviderId(1L);
        request.setDeliveryDate(LocalDate.now());
        request.setProducts(List.of(prodDto));

        deliveryService.acceptDelivery(request);

        verify(deliveryRepository).save(any(Delivery.class));
    }

    @Test
    void getDeliveriesReportBetweenDates_shouldReturnReport() {

        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now();

        List<Delivery> deliveries =
                List.of(new Delivery());

        when(deliveryRepository
                .findByDeliveryDateBetween(start,end))
                .thenReturn(deliveries);

        DeliveryReportDto expected =
                new DeliveryReportDto();

        when(deliveryReporter.makeReport(deliveries))
                .thenReturn(expected);

        DeliveryReportDto result =
                deliveryService
                        .getDeliveriesReportBetweenDates(start,end);

        assertEquals(expected,result);
    }

    @Test
    void getDeliveriesReportBetweenDates_shouldThrowException() {

        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().minusDays(1);

        assertThrows(
                BusinessException.class,
                () -> deliveryService
                        .getDeliveriesReportBetweenDates(start,end)
        );
    }
}

