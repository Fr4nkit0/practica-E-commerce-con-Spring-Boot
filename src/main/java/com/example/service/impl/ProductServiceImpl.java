package com.example.service.impl;

import com.example.dto.request.SaveProduct;
import com.example.dto.response.GetProduct;
import com.example.exceptions.ResourceNotFoundException;
import com.example.mapper.ProductMapper;

import com.example.persistence.entity.Product;
import com.example.persistence.repository.CategoryRepository;
import com.example.persistence.repository.ProductRepository;
import com.example.service.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Page<GetProduct> findAll(Pageable pageable) {
        Page<Product> entities = productRepository.findAll(pageable);
        if (entities.isEmpty()) throw  new ResourceNotFoundException("empty records");
        return entities.map(ProductMapper::toGetDto);
    }

    @Override
    public GetProduct findById(Integer id) {
        Product product = findByIdEntity(id);
        return ProductMapper.toGetDto(product);
    }

    @Override
    public GetProduct createOne(SaveProduct saveProduct) {
        if (!categoryRepository.existsById(saveProduct.categoryId()))throw  new RuntimeException("No existe la categoria") ;
        return ProductMapper.toGetDto(productRepository.save(ProductMapper.toGetEntity(saveProduct))) ;
    }

    @Override
    public GetProduct updateOne(SaveProduct updateProduct, Integer id) {
        Product oldProduct = findByIdEntity(id) ;
        ProductMapper.updateEntity(oldProduct,updateProduct);
        return ProductMapper.toGetDto(productRepository.save(oldProduct)) ;
    }


    private Product findByIdEntity (Integer id){
        return productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe el id:"+id));
    }
}

