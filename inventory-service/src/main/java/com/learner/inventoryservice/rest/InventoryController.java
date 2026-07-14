package com.learner.inventoryservice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	@GetMapping("/check_product/{productId}")
	public String checkStock(@PathVariable String productId) {
		System.out.println("Checking stock for: " + productId);
        return "IN_STOCK";	
	}
}
