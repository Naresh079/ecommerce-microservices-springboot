package com.learner.orderservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventConsumer {

	    @KafkaListener(topics = "PAYMENT_SUCCESS", groupId = "microservices-group")
	    public void handlePaymentSuccess(String productId) {
	        System.out.println("OrderService: payment confirmed for: " + productId);
	        System.out.println("OrderService: ✅ ORDER CONFIRMED for: " + productId);
	    }

	    @KafkaListener(topics = "ORDER_CANCELLED", groupId = "microservices-group")
	    public void handleOrderCancelled(String productId) {
	        System.out.println("OrderService: ❌ Order cancelled for: " + productId);
	    }

}
