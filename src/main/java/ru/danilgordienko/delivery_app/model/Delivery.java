package ru.danilgordienko.delivery_app.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ElementCollection
    @CollectionTable(name = "delivery_products", joinColumns = @JoinColumn(name = "delivery_id"))
    private List<DeliveryProduct> products;

    private LocalDate deliveryDate;

}