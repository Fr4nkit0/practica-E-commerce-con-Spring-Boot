package com.example.controller;


import com.example.dto.request.SaveCategory;
import com.example.dto.response.GetCategory;
import com.example.dto.response.GetCategoryStatistic;
import com.example.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Page<GetCategory>> findAll (Pageable pageable){
        return ResponseEntity.ok(categoryService.findALl(pageable));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<GetCategoryStatistic> findOneById (@PathVariable Integer categoryId){
        return ResponseEntity.ok(categoryService.findById(categoryId));
    }
    @PostMapping
    public ResponseEntity<GetCategory> createOne (@RequestBody @Valid SaveCategory saveCategory){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createOne(saveCategory));
    }
    @PutMapping("{categoryId}")
    public ResponseEntity<GetCategory> updateOneById(@RequestBody @Valid SaveCategory updateCategory,
                                                  @PathVariable Integer categoryId){
        return ResponseEntity.ok(categoryService.updateById(updateCategory,categoryId));
    }
    @PutMapping("/{categoryId}/disabled")
    public ResponseEntity<GetCategory> disableOneById (Integer categoryId){
        return ResponseEntity.ok(categoryService.disableOneById(categoryId));
    }






}
