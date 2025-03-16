package ru.danilgordienko.delivery_app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.danilgordienko.delivery_app.model.Product;
import ru.danilgordienko.delivery_app.model.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
