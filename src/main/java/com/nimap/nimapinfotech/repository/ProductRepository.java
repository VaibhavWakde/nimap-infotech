package com.nimap.nimapinfotech.repository;

import com.nimap.nimapinfotech.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
