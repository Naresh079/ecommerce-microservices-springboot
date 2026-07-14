package com.learner.orderservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishOrderCreated(String productId) {
        System.out.println("Publishing ORDER_CREATED for: " + productId);
        kafkaTemplate.send("ORDER_CREATED", productId);
    }

    public void publishOrderCancelled(String productId) {
        System.out.println("Publishing ORDER_CANCELLED for: " + productId);
        kafkaTemplate.send("ORDER_CANCELLED", productId);
    }
}
