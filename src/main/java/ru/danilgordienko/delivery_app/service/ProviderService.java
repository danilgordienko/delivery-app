package ru.danilgordienko.delivery_app.service;


import org.springframework.stereotype.Service;
import ru.danilgordienko.delivery_app.model.Provider;
import ru.danilgordienko.delivery_app.repository.ProviderRepository;

import java.util.List;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }
}
