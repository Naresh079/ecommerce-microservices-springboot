package com.learner.orderservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.learner.orderservice.entity.OutboxEvent;

public interface OutBoxRepository extends CrudRepository<OutboxEvent, Long>{

	List<OutboxEvent> findByStatus(String string);

}
