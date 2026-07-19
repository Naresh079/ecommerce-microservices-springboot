package com.learner.orderservice.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.learner.orderservice.entity.OutboxEvent;
import com.learner.orderservice.repository.OutBoxRepository;

import jakarta.transaction.Transactional;

@Component
public class OutboxScheduler {
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	OutBoxRepository outboxRepository;
	@Scheduled(fixedDelay = 30000)
	@Transactional
	public void publishPendingEvents() {
	    System.out.println("Scheduler running...");
	    List<OutboxEvent> pendingEvents = outboxRepository.findByStatus("PENDING");
	    System.out.println("Pending events found: " + pendingEvents.size());
	    
	    for(OutboxEvent event : pendingEvents) {
	        kafkaTemplate.send(event.getTopic(), event.getPayload());
	        System.out.println("Event published: " + event.getPayload() + " to " + event.getTopic());
	        event.setStatus("PUBLISHED");
	        outboxRepository.save(event);
	    }
	}
}
