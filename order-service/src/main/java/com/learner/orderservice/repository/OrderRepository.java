package com.learner.orderservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.learner.orderservice.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
