package com.example.controller;

import com.example.dto.ProductDto;
import com.example.persistence.entity.Product;
import com.example.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("api/v1")
public class ProductController {
    private final ProductService productService ;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAll (){
        List<Product> products = productService.findAll() ;
        if (products.isEmpty()){
            throw  new RuntimeException("Registros vacios") ;
        }
        return ResponseEntity.ok(products) ;
    }
    @GetMapping ("/product/{productId}")
    public ResponseEntity<Product> findById (@PathVariable Integer productId ){
        Product product = productService.findById(productId);
        return ResponseEntity.ok(product);
    }
    @PostMapping("/product")
    public ResponseEntity<Product> createProduct (@RequestBody ProductDto productDto){
        Product newProduct = productService.createOne(productDto) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }
    @PutMapping ("/product/{productId}")
    public ResponseEntity<Product> updateProduct (@PathVariable Integer productId ,
                                                  @RequestBody ProductDto productDto){
        Product updateProduct = productService.updateOne(productDto,productId) ;
        return ResponseEntity.ok(updateProduct);
    }









}
