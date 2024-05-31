package com.example.service.impl;

import com.example.dto.ProductDto;
import com.example.persistence.entity.Product;
import com.example.persistence.repository.ProductRepository;
import com.example.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository ;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con id:" + id)));

    }

    @Override
    public Product createOne(ProductDto productDto) {
        Product newProduct = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();
        return productRepository.save(newProduct) ;
    }

    @Override
    public Product updateOne(ProductDto productDto, Integer id) {
        Product updateProduct = findById(id) ;
        updateProduct.setName(productDto.getName());
        updateProduct.setDescription(productDto.getDescription());
        updateProduct.setPrice(productDto.getPrice());
        return productRepository.save(updateProduct);
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        if (!StringUtils.hasText(name)) throw  new RuntimeException("Text Vacio") ;
        return productRepository.findByNameContaining(name);
    }
}
