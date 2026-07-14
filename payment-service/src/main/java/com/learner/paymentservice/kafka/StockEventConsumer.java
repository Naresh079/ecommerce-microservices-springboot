package com.learner.paymentservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class StockEventConsumer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "STOCK_UPDATED", groupId = "microservices-group")
    public void handleStockUpdated(String productId) {
        System.out.println("PaymentService: processing payment for: " + productId);
        System.out.println("PaymentService: payment done, publishing PAYMENT_SUCCESS");
        kafkaTemplate.send("PAYMENT_SUCCESS", productId);
    }
}
