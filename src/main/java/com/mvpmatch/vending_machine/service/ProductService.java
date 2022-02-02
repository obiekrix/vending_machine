package com.mvpmatch.vending_machine.service;

import com.mvpmatch.vending_machine.entity.Product;
import com.mvpmatch.vending_machine.entity.User;
import com.mvpmatch.vending_machine.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(Pageable pageable) {
        Page<Product> pagedProducts = productRepository.findAll(pageable);
        return pagedProducts;
    }

    public Product findById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.isPresent() ? optionalProduct.get() : null;
    }

    public Product create(Product product, User user) {
        product.setUser(user);
        return productRepository.save(product);
    }
}
