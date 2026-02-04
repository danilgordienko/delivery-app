package ru.danilgordienko.delivery_app.model.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DeliveryProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Delivery delivery;

    @ManyToOne
    private Product product;

    private Double weight;
}

