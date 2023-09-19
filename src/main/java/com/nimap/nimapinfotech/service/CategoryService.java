package com.nimap.nimapinfotech.service;

import com.nimap.nimapinfotech.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<Page<Category>> getAllCategories(Pageable pageable);

    ResponseEntity<?> saveCategory(Category category);

    ResponseEntity<?> getCategoryById(Long id);

    ResponseEntity<?> updateCategory(Long id, Category category);

    ResponseEntity<?> deleteCategory(Long id);

}
