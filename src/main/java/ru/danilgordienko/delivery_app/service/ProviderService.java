package ru.danilgordienko.delivery_app.service;

import ru.danilgordienko.delivery_app.model.dto.ProviderDto;
import ru.danilgordienko.delivery_app.model.entity.Provider;

import java.util.List;

public interface ProviderService {

    List<ProviderDto> getAllProviders();

    Provider getProviderById(Long id);

    ProviderDto getProviderDtoById(Long id);
}
