package ru.danilgordienko.delivery_app.model.mapping;

import org.mapstruct.Mapper;
import ru.danilgordienko.delivery_app.model.dto.ProviderDto;
import ru.danilgordienko.delivery_app.model.entity.Provider;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProviderMapper {
    ProviderDto toDto(Provider provider);

    List<ProviderDto> toListProviderDto(List<Provider> providerList);
}

