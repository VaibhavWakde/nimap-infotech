package com.nimap.nimapinfotech.service;

import com.nimap.nimapinfotech.entities.Category;
import com.nimap.nimapinfotech.repository.CategoryRepository;
import com.nimap.nimapinfotech.utils.CommonMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<Page<Category>> getAllCategories(Pageable pageable) {
        try {
            Page<Category> categories = (Page<Category>) categoryRepository.findAll(pageable);
            if (categories.isEmpty())
                return (ResponseEntity<Page<Category>>) CommonMethods.handleResponse("No Categories Found",204);
            return (ResponseEntity<Page<Category>>) CommonMethods.handleResponse("Category Found",200,categories);
        }
        catch (Exception e) {
            return (ResponseEntity<Page<Category>>) CommonMethods.handleResponse(e.getMessage(),500);
        }
    }

    @Override
    public ResponseEntity<?> saveCategory(Category category) {
        try {
            if (category.getName() == null ||   category.getName().isEmpty())
                return CommonMethods.handleResponse("Category Should Not be Empty or Null",400);
            Category c = categoryRepository.save(category);
            return CommonMethods.handleResponse("Category Saved Successfully",200,c);
        }
        catch (Exception e) {
            return CommonMethods.handleResponse(e.getMessage(),500);
        }
    }

    @Override
    public ResponseEntity<?> getCategoryById(Long id) {
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if (!category.isPresent())
                return CommonMethods.handleResponse("Invalid Category with Id "+id,204);
            return CommonMethods.handleResponse("Category Found with Id "+id,200, category);
        }
        catch (Exception e) {
            return CommonMethods.handleResponse(e.getMessage(),500);
        }
    }

    @Override
    public ResponseEntity<?> updateCategory(Long id, Category category) {
        try {
            Optional<Category> c = categoryRepository.findById(id);
            if (!c.isPresent())
                return CommonMethods.handleResponse("Category with id "+id+" Not Found",204);
            if (category.getName().isEmpty() || category.getName() == null)
                return CommonMethods.handleResponse("Category Name Should Not be Empty or Null",400);
            c.get().setName(category.getName());
            Category c1 = categoryRepository.save(c.get());
            return CommonMethods.handleResponse("Category Updated Successfully",200,c1);
        } catch (Exception e) {
            return CommonMethods.handleResponse(e.getMessage(),500);
        }
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long id) {
        try {
            Optional<Category> category = categoryRepository.findById(id);
            if (!category.isPresent())
                return CommonMethods.handleResponse("Category with id "+id+" Not Found",204);
            categoryRepository.delete(category.get());
            return CommonMethods.handleResponse("Category Deleted Successfully",200);
        }
        catch (Exception e) {
            return CommonMethods.handleResponse(e.getMessage(),500);
        }
    }
}
