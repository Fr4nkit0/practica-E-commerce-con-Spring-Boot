package com.example.controller;

import com.example.dto.CategoryDto;
import com.example.dto.ProductDto;
import com.example.exceptions.ResourceNotFoundException;
import com.example.persistence.entity.Category;
import com.example.persistence.entity.Product;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2")
public class CategoryController {
    private final CategoryService categoryService ;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> findAll (){
        List<Category> categories = categoryService.findAll() ;
        if (categories.isEmpty()){throw  new ResourceNotFoundException("Registros vacios") ;}
        return ResponseEntity.ok(categories) ;
    }
    @GetMapping ("/product/{categoryId}")
    public ResponseEntity<Category> findById (@PathVariable Integer categorytId ){
        Category category = categoryService.findById(categorytId);
        return ResponseEntity.ok(category);
    }
    @PostMapping("/product")
    public ResponseEntity<Category> createProduct (@RequestBody CategoryDto categoryDto){
        Category newCategory = categoryService.createOne(categoryDto) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }
    @PutMapping ("/product/{categoryId}")
    public ResponseEntity<Category> updateProduct (@PathVariable Integer categoryId ,
                                                  @RequestBody CategoryDto categoryDto){
        Category updateCategory= categoryService.updateOne(categoryDto,categoryId) ;
        return ResponseEntity.ok(updateCategory);
    }
}
