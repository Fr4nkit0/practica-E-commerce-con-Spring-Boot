package com.example.service.impl;

import com.example.dto.request.SaveCategory;
import com.example.dto.response.GetCategory;
import com.example.dto.response.GetCategoryStatistic;
import com.example.exceptions.ResourceNotFoundException;
import com.example.mapper.CategoryMapper;
import com.example.persistence.entity.Category;
import com.example.persistence.repository.CategoryRepository;
import com.example.persistence.repository.ProductRepository;
import com.example.persistence.util.Status;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Page<GetCategory> findALl(Pageable pageable) {
        Page<Category> page = categoryRepository.findAll(pageable);
        if (page.isEmpty()) throw  new ResourceNotFoundException("empty records");
        return page.map(CategoryMapper::toGetDto);
    }

    @Override
    public GetCategoryStatistic findById(Integer id) {
        return CategoryMapper.toGetCategoryStatistic(
                findByIdEntity(id),
                productRepository.countByCategoryId(id),
                productRepository.avgPriceByCategoryId(id),
                productRepository.minPriceByCategoryId(id),
                productRepository.maxPriceByCategoryId(id)
        );
    }

    @Override
    public GetCategory createOne(SaveCategory saveCategory) {
        Category categorySaved=CategoryMapper.toGetEntity(saveCategory);
        return CategoryMapper.toGetDto(categoryRepository.save(categorySaved));
    }

    @Override
    public GetCategory updateById(SaveCategory updateCategory, Integer id) {
        Category oldCategory=findByIdEntity(id);
        CategoryMapper.updateEntity(updateCategory,oldCategory);
        return CategoryMapper.toGetDto(categoryRepository.save(oldCategory));
    }

    @Override
    public GetCategory disableOneById(Integer id) {
        Category disableCategory=findByIdEntity(id);
        disableCategory.setStatus(Status.DISABLED);
        return CategoryMapper.toGetDto(disableCategory);
    }
    private  Category findByIdEntity (Integer categoryId){
        return categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("category does not exist id :"+categoryId));
    }
}
