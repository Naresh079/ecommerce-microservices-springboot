package com.learner.orderservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learner.orderservice.entity.Order;
import com.learner.orderservice.entity.OutboxEvent;
import com.learner.orderservice.repository.OrderRepository;
import com.learner.orderservice.repository.OutBoxRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OutBoxRepository outboxEventRepository;

    @GetMapping("/place/{productId}")
    @Transactional
    public String placeOrder(@PathVariable String productId) {

        // Save order to DB
        Order order = new Order(productId, "PENDING");
        orderRepository.save(order);
        System.out.println("Order saved to DB: " + productId);

        // Save event to Outbox in SAME transaction
        OutboxEvent event = new OutboxEvent("ORDER_CREATED", productId);
        outboxEventRepository.save(event);
        System.out.println("Outbox event saved: " + productId);

        return "Order request received for: " + productId + ". Processing asynchronously.";
    }
}