package ru.danilgordienko.delivery_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danilgordienko.delivery_app.model.Delivery;
import ru.danilgordienko.delivery_app.model.Product;

import java.time.LocalDate;
import java.util.List;


public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findByDeliveryDateBetween(LocalDate startDate, LocalDate endDate);
}
