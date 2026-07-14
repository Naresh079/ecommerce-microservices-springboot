package com.learner.inventoryservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
@Component
public class OrderEventConsumer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "ORDER_CREATED", groupId = "microservices-group")
    public void handleOrderCreated(String productId) {
        System.out.println("InventoryService: checking stock for: " + productId);
        System.out.println("InventoryService: stock confirmed, publishing STOCK_UPDATED");
        kafkaTemplate.send("STOCK_UPDATED", productId);
    }
}	