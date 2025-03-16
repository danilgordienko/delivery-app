package ru.danilgordienko.delivery_app.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
public class DeliveryReport {
    private List<Delivery> deliveries = new ArrayList<>();
    private Double totalWeight = 0.0;
    private Double totalCost = 0.0;

    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
        totalWeight += delivery.getProducts().stream().mapToDouble(DeliveryProduct::getWeight).sum();
        totalCost += delivery.getProducts().stream().mapToDouble(x -> x.getProduct().getPrice() * x.getWeight()).sum();
    }

    public Map<Product, Double> getReport(){
        Map<Product, Double> rep = new HashMap<>();
        for(var deliver: deliveries){
            for(var product: deliver.getProducts()){
                rep.putIfAbsent(product.getProduct(), 0.0);
                rep.put(product.getProduct(), rep.get(product.getProduct()) + product.getWeight());
            }
        }
        return rep;
    }


}
