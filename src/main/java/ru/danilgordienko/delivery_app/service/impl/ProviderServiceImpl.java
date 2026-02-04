package ru.danilgordienko.delivery_app.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.danilgordienko.delivery_app.exceptions.ProviderNotFoundException;
import ru.danilgordienko.delivery_app.model.dto.ProductDto;
import ru.danilgordienko.delivery_app.model.dto.ProviderDto;
import ru.danilgordienko.delivery_app.model.entity.Provider;
import ru.danilgordienko.delivery_app.model.mapping.ProviderMapper;
import ru.danilgordienko.delivery_app.repository.ProviderRepository;
import ru.danilgordienko.delivery_app.service.ProviderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl  implements ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    // получение всех поставщиков
    public List<ProviderDto> getAllProviders() {
        var providers = providerRepository.findAll();
        return providerMapper.toListProviderDto(providers);
    }

    // получение поставщика по id
    @Override
    public Provider getProviderById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new ProviderNotFoundException("Provider with id " + id + " not found")
        );
    }

    // получение ProviderDto по id
    @Override
    public ProviderDto getProviderDtoById(Long id) {
        return providerMapper.toDto(getProviderById(id));
    }
}
