package com.example.controller;

import com.example.dto.request.SaveProduct;
import com.example.dto.response.GetProduct;
import com.example.persistence.entity.Product;
import com.example.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products")
    public ResponseEntity<List<GetProduct>> findAll (){
        return ResponseEntity.ok(productService.findAll()) ;}
    @GetMapping("/product/{productId}")
    public ResponseEntity<GetProduct> findById (@PathVariable Integer productId){
        return ResponseEntity.ok(productService.findById(productId));
    }
    @PostMapping("/product")
    public ResponseEntity<GetProduct> createOne (@RequestBody @Valid  SaveProduct saveProduct){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createOne(saveProduct));
    }
    @PutMapping("/product/{productId}")
    public ResponseEntity<GetProduct> updateOne (@RequestBody @Valid  SaveProduct updateProduct,
                                                 @PathVariable Integer productId){
        return ResponseEntity.ok(productService.updateOne(updateProduct,productId));
    }
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteOne (Integer productId){
        productService.deleteOne(productId);
        return ResponseEntity.noContent().build();
    }






}
