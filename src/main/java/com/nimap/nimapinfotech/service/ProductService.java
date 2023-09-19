package com.nimap.nimapinfotech.service;

import com.nimap.nimapinfotech.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<Page<Product>> getAllProduct(Pageable pageable);

    ResponseEntity<?> saveProduct(Product product);

    ResponseEntity<?> getProductById(Long id);

    ResponseEntity<?> updateProduct(Long id, Product product);

    ResponseEntity<?> deleteProduct(Long id);

}

