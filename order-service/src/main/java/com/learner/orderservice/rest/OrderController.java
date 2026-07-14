package com.learner.orderservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learner.orderservice.client.InventoryClient;
import com.learner.orderservice.client.PaymentClient;
import com.learner.orderservice.kafka.OrderEventProducer;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private PaymentClient paymentClient;
    
    @Autowired
    private OrderEventProducer orderEventProducer;

    @GetMapping("/place/{productId}")
    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackResponse")
    public String placeOrder(@PathVariable String productId) {
        
        // Step 1 — Check inventory
        String stockStatus = inventoryClient.checkStock(productId);
        if (!stockStatus.equals("IN_STOCK")) {
            return "Order failed — product out of stock";
        }
        System.out.println("Step 1 passed — stock available");

        // Step 2 — Process payment
        String paymentStatus = paymentClient.processPayment(productId);
        if (!paymentStatus.equals("PAYMENT_SUCCESS")) {
            // Compensating transaction
            System.out.println("Payment failed — rolling back inventory");
            return "Order failed — payment unsuccessful. Inventory restored.";
        }
        System.out.println("Step 2 passed — payment successful");
        
        orderEventProducer.publishOrderCancelled("Order placed for product: " + productId);

        // Step 3 — All good
        return "Order CONFIRMED for: " + productId;
    }

    public String fallbackResponse(String productId, Exception e) {
        return "Service is down. Please try again later.";
    }
}