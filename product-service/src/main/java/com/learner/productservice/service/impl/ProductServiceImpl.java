package com.learner.productservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learner.productservice.dto.ProductRequestDTO;
import com.learner.productservice.dto.ProductResponseDTO;
import com.learner.productservice.entity.Product;
import com.learner.productservice.repository.ProductRepository;
import com.learner.productservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    public ProductServiceImpl(ProductRepository repository){this.repository=repository;}

    @Override
    public ProductResponseDTO create(ProductRequestDTO request){
    	System.out.println("the post request is here...");
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(request.getCategory())
                .imageUrl(request.getImageUrl())
                .active(true)
                .build();
        System.out.println("product saved ....");
        return map(repository.save(product));
    }

    @Override
    public List<ProductResponseDTO> getAll(){
        return repository.findAll().stream().map(this::map).toList();
    }

    @Override
    public ProductResponseDTO getById(Long id){
        return map(repository.findById(id).orElseThrow());
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO request){
        Product p = repository.findById(id).orElseThrow();
        p.setName(request.getName());
        p.setDescription(request.getDescription());
        p.setPrice(request.getPrice());
        p.setQuantity(request.getQuantity());
        p.setCategory(request.getCategory());
        p.setImageUrl(request.getImageUrl());
        return map(repository.save(p));
    }

    @Override
    public void delete(Long id){ repository.deleteById(id);}    

    private ProductResponseDTO map(Product p){
        return ProductResponseDTO.builder().id(p.getId()).name(p.getName()).description(p.getDescription()).price(p.getPrice()).quantity(p.getQuantity()).category(p.getCategory()).imageUrl(p.getImageUrl()).active(p.getActive()).build();
    }
}
