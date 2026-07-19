package com.learner.orderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "outbox_events")
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String payload;
    private String status; // PENDING or PUBLISHED

    public OutboxEvent() {}

    public OutboxEvent(String topic, String payload) {
        this.topic = topic;
        this.payload = payload;
        this.status = "PENDING";
    }

    // getters and setters
    public Long getId() { return id; }
    public String getTopic() { return topic; }
    public String getPayload() { return payload; }
    public String getStatus() { return status; }
    public void setId(Long id) { this.id = id; }
    public void setTopic(String topic) { this.topic = topic; }
    public void setPayload(String payload) { this.payload = payload; }
    public void setStatus(String status) { this.status = status; }
}
