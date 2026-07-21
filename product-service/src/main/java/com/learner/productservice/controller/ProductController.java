package com.learner.productservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learner.productservice.dto.ProductRequestDTO;
import com.learner.productservice.dto.ProductResponseDTO;
import com.learner.productservice.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service){this.service=service;}

    @PostMapping
    public ProductResponseDTO create( @RequestBody ProductRequestDTO request){return service.create(request);}    

    @GetMapping
    public List<ProductResponseDTO> getAll(){return service.getAll();}

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id){return service.getById(id);}    

    @PutMapping("/{id}")
    public ProductResponseDTO update(@PathVariable Long id, @RequestBody ProductRequestDTO request){return service.update(id,request);}    

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){service.delete(id);}    
}
