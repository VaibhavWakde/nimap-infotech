package com.nimap.nimapinfotech.service;


import com.nimap.nimapinfotech.entities.Category;
import com.nimap.nimapinfotech.entities.Product;
import com.nimap.nimapinfotech.repository.CategoryRepository;
import com.nimap.nimapinfotech.repository.ProductRepository;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public ResponseEntity<Page<Product>> getAllProduct(Pageable pageable) {
        try {
            Page<Product> products = (Page<Product>) productRepository.findAll(pageable);
            if (products.isEmpty())
                return (ResponseEntity<Page<Product>>) CommonMethods.handleResponse("No products Found", 204);

            return (ResponseEntity<Page<Product>>) CommonMethods.handleResponse("Product Found", 200, products);
        } catch (Exception e) {
            return (ResponseEntity<Page<Product>>) CommonMethods.handleResponse(e.getMessage(), 500);
        }
    }

    @Override
    public ResponseEntity<?> getProductById(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (!product.isPresent())
                return CommonMethods.handleResponse("Invalid Product with Id " + id, 204);
            return CommonMethods.handleResponse("Product Found with Id " + id, 200, product);
        } catch (Exception e) {
            return CommonMethods.handleResponse(e.getMessage(), 500);
        }
    }

    @Override
    public ResponseEntity<?> updateProduct(Long id, Product product) {
        try {
            Optional<Product> p = productRepository.findById(id);
            if (!p.isPresent())
                return CommonMethods.handleResponse("Product with id " + id + " Not Found", 204);
            if (product.getName().isEmpty() || product.getName() == null
                    || product.getPrice() == null || product.getPrice().isNaN()
                    || product.getCategory()  == null
            )
                return CommonMethods.handleResponse("Product Name or Price or Category Should Not be Empty or Null", 400);
            Optional<Category> category = categoryRepository.findById(product.getCategory().getId());
            if (!category.isPresent()) {
                return CommonMethods.handleResponse("Invalid Category Id",400);
            }
            p.get().setName(product.getName());
            p.get().setPrice(product.getPrice());
            p.get().setCategory(category.get());
            Product p1 = productRepository.save(p.get());
            return CommonMethods.handleResponse("Product Updated Successfully", 200, p1);
        } catch (Exception e) {
            return CommonMethods.handleResponse(e.getMessage(), 500);
        }
    }

    @Override
    public ResponseEntity<?> saveProduct(Product product) {
        try {
            if (product.getName() == null || product.getName().isEmpty()
                    || product.getPrice() == null || product.getPrice().isNaN()
                    || product.getCategory() == null
            )
                return CommonMethods.handleResponse("Product Should Not be Empty or Null", 400);
            Optional<Category> category = categoryRepository.findById(product.getCategory().getId());
            if (!category.isPresent()) {
                return CommonMethods.handleResponse("Invalid Category Id",400);
            }
            product.setCategory(category.get());
            Product p = productRepository.save(product);
            return CommonMethods.handleResponse("Product Saved Successfully", 200, p);
        } catch (Exception e) {
            return CommonMethods.handleResponse(e.getMessage(), 500);
        }
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (!product.isPresent())
                return CommonMethods.handleResponse("Product with id " + id + " Not Found", 204);
            productRepository.delete(product.get());
            return CommonMethods.handleResponse("Product Deleted Successfully", 200);
        } catch (Exception e) {
            return CommonMethods.handleResponse(e.getMessage(), 500);
        }
    }
}

