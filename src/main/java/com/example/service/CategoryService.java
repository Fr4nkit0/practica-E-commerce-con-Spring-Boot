package com.example.service;

import com.example.dto.CategoryDto;
import com.example.dto.ProductDto;
import com.example.persistence.entity.Category;
import com.example.persistence.entity.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll () ;
    Category findById (Integer id);
    Category createOne (CategoryDto categoryDto) ;
    Category updateOne (CategoryDto categoryDto , Integer id) ;
    List<Category> findByNameContaining (String name) ;
}
