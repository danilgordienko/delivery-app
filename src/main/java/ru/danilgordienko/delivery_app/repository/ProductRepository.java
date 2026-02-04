package ru.danilgordienko.delivery_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danilgordienko.delivery_app.model.entity.Product;



public interface ProductRepository  extends JpaRepository<Product, Long> {

}
