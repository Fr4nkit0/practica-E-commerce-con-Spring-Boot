package com.example.service.impl;

import com.example.dto.CategoryDto;
import com.example.exceptions.ResourceNotFoundException;
import com.example.persistence.entity.Category;
import com.example.persistence.repository.CategoryRepository;
import com.example.persistence.util.Status;
import com.example.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository ;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return  categoryRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("No se encontro la category con id:" + id)) ;
    }

    @Override
    public Category createOne(CategoryDto categoryDto) {
        Category newCategory = Category.builder()
                .name(categoryDto.getName())
                .Status(Status.ENABLED)
                .build();
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category updateOne(CategoryDto categoryDto, Integer id) {
        Category updateCategory = findById(id);
        updateCategory.setName(categoryDto.getName());
        return categoryRepository.save(updateCategory);
    }

    @Override
    public List<Category> findByNameContaining(String name) {
        if (!StringUtils.hasText(name)) throw  new RuntimeException("Text Vacio") ;
        return categoryRepository.findByNameContaining(name);
    }
}
