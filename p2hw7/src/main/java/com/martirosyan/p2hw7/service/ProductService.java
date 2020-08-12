package com.martirosyan.p2hw7.service;

import com.martirosyan.p2hw7.model.Product;
import com.martirosyan.p2hw7.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void delete(Long id) {
        productRepository.delete(findById(id));
    }
}
