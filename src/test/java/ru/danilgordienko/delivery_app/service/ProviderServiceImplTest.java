package ru.danilgordienko.delivery_app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.danilgordienko.delivery_app.exceptions.ProviderNotFoundException;
import ru.danilgordienko.delivery_app.model.dto.ProviderDto;
import ru.danilgordienko.delivery_app.model.entity.Provider;
import ru.danilgordienko.delivery_app.model.mapping.ProviderMapper;
import ru.danilgordienko.delivery_app.repository.ProviderRepository;
import ru.danilgordienko.delivery_app.service.impl.ProviderServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProviderServiceImplTest {

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private ProviderMapper providerMapper;

    @InjectMocks
    private ProviderServiceImpl providerService;

    @Test
    void getAllProviders_shouldReturnDtoList() {

        List<Provider> providers =
                List.of(new Provider(1L,"Provider1"));

        List<ProviderDto> dtos =
                List.of(new ProviderDto(1L,"Provider1"));

        when(providerRepository.findAll())
                .thenReturn(providers);

        when(providerMapper.toListProviderDto(providers))
                .thenReturn(dtos);

        List<ProviderDto> result =
                providerService.getAllProviders();

        assertEquals(1,result.size());

        verify(providerRepository).findAll();
        verify(providerMapper).toListProviderDto(providers);
    }

    @Test
    void getProviderById_shouldReturnProvider() {

        Provider provider =
                new Provider(1L,"Provider1");

        when(providerRepository.findById(1L))
                .thenReturn(Optional.of(provider));

        Provider result =
                providerService.getProviderById(1L);

        assertEquals(provider,result);
    }

    @Test
    void getProviderById_shouldThrowException() {

        when(providerRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ProviderNotFoundException.class,
                () -> providerService.getProviderById(1L)
        );
    }

    @Test
    void getProviderDtoById_shouldReturnDto() {

        Provider provider =
                new Provider(1L,"Provider1");

        ProviderDto dto =
                new ProviderDto(1L,"Provider1");

        when(providerRepository.findById(1L))
                .thenReturn(Optional.of(provider));

        when(providerMapper.toDto(provider))
                .thenReturn(dto);

        ProviderDto result =
                providerService.getProviderDtoById(1L);

        assertEquals(dto,result);
    }
}
