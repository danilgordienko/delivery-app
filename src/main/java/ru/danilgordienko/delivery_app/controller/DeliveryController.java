package ru.danilgordienko.delivery_app.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.danilgordienko.delivery_app.model.dto.*;
import ru.danilgordienko.delivery_app.model.entity.Delivery;
import ru.danilgordienko.delivery_app.model.entity.Provider;
import ru.danilgordienko.delivery_app.service.DeliveryService;
import ru.danilgordienko.delivery_app.service.ProductService;
import ru.danilgordienko.delivery_app.service.ProviderService;

import java.time.LocalDate;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/deliveries")
@AllArgsConstructor
@Slf4j
@Tag(name = "Поставки", description = "API для управления поставками")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Operation(
            summary = "Принять поставку",
            description = "Регистрирует новую поставку от поставщика с указанными продуктами"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Поставка успешно принята"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные запроса"),
            @ApiResponse(responseCode = "404", description = "Поставщик или продукт не найден")
    })
    @PostMapping
    public ResponseEntity<String> acceptDelivery(
            @RequestBody @Valid DeliveryRequestDto dto
    ) {
        log.info("[POST /api/deliveries] Request to accept delivery for provider {}, product count {}",
                dto.getProviderId(), dto.getProducts().size());

        deliveryService.acceptDelivery(dto);

        log.info("[POST /api/deliveries] Accept delivery for provider {}, product count {}",
                dto.getProviderId(), dto.getProducts().size());

        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Получить отчет по поставкам",
            description = "Возвращает отчет по поставкам за указанный период"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отчет успешно получен"),
            @ApiResponse(responseCode = "400", description = "Некорректные даты")
    })
    @GetMapping("/report")
    public ResponseEntity<DeliveryReportDto> getReport(
            @Parameter(description = "Дата начала периода", example = "2025-01-01")
            @RequestParam LocalDate startDate,

            @Parameter(description = "Дата окончания периода", example = "2025-01-31")
            @RequestParam LocalDate endDate) {

        log.info("[GET /api/deliveries] Request to get report for date {} - {}", startDate, endDate);

        var report = deliveryService.getDeliveriesReportBetweenDates(startDate, endDate);

        log.info("[GET /api/deliveries] get report for date {} - {}", startDate, endDate);

        return ResponseEntity.ok(report);
    }
}

