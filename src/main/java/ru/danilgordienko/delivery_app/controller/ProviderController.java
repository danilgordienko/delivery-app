package ru.danilgordienko.delivery_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.danilgordienko.delivery_app.model.dto.ProviderDto;
import ru.danilgordienko.delivery_app.service.ProviderService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/providers")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Поставщики", description = "API для работы с поставщиками")
public class ProviderController {

    private final ProviderService providerService;

    @Operation(
            summary = "Получить список поставщиков",
            description = "Возвращает список всех поставщиков"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список поставщиков успешно получен")
    })
    @GetMapping
    public ResponseEntity<List<ProviderDto>> getAllProviders() {

        log.info("[GET /api/providers] Request to get all providers");

        var providers = providerService.getAllProviders();

        log.info("[GET /api/providers] Get {} providers", providers.size());

        return ResponseEntity.ok(providers);
    }
}


