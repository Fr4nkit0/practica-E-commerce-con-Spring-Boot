package com.example.controller;

import com.example.dto.request.SaveProduct;
import com.example.dto.response.GetProduct;
import com.example.persistence.entity.Product;
import com.example.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<Page<GetProduct>> findAll (Pageable pageable){
        return ResponseEntity.ok(productService.findAll(pageable)) ;}
    @GetMapping("/{productId}")
    public ResponseEntity<GetProduct> findById (@PathVariable Integer productId){
        return ResponseEntity.ok(productService.findById(productId));
    }
    @PostMapping
    public ResponseEntity<GetProduct> createOne (@RequestBody @Valid  SaveProduct saveProduct){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createOne(saveProduct));
    }
    @PutMapping("/{productId}")
    public ResponseEntity<GetProduct> updateOne (@RequestBody @Valid  SaveProduct updateProduct,
                                                 @PathVariable Integer productId){
        return ResponseEntity.ok(productService.updateOne(updateProduct,productId));
    }







}
