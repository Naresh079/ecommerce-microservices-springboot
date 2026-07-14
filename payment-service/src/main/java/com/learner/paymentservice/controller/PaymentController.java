package com.learner.paymentservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@GetMapping("/process/{orderId}")
	public String processPayment(@PathVariable String orderId) {
	    System.out.println("Processing payment for order: " + orderId);
	    return "PAYMENT_SUCCESS";
	}
}
