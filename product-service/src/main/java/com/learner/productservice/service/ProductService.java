package com.learner.productservice.service;

import java.util.List;

import com.learner.productservice.dto.ProductRequestDTO;
import com.learner.productservice.dto.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO create(ProductRequestDTO request);
    List<ProductResponseDTO> getAll();
    ProductResponseDTO getById(Long id);
    ProductResponseDTO update(Long id, ProductRequestDTO request);
    void delete(Long id);
}
