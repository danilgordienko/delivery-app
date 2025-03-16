package ru.danilgordienko.delivery_app.model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Embeddable
public class DeliveryProduct {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Double weight;
}
