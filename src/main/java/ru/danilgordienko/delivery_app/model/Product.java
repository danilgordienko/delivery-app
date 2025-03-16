package ru.danilgordienko.delivery_app.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Double price;
}
