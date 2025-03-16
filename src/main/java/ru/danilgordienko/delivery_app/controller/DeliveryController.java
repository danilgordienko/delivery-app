package ru.danilgordienko.delivery_app.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.danilgordienko.delivery_app.model.*;
import ru.danilgordienko.delivery_app.service.DeliveryService;
import ru.danilgordienko.delivery_app.service.ProductService;
import ru.danilgordienko.delivery_app.service.ProviderService;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/api/deliveries")
@AllArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final ProviderService providerService;
    private final ProductService productService;

    @GetMapping
    public String getDeliveryForm(Model model) {
        model.addAttribute("providers", providerService.getAllProviders());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("delivery", new Delivery());
        return "index";
    }

    @PostMapping
    public String createDelivery(@ModelAttribute Delivery delivery) {
        deliveryService.acceptDelivery(delivery);

        return "redirect:/api/deliveries";
    }

    @GetMapping("/report")
    public String getReport(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, Model model) {
        Map<Provider, DeliveryReport> reportData = deliveryService.getDeliveriesReportBetweenDates(startDate, endDate);
        model.addAttribute("reportData", reportData);
        return "index";
    }
}
