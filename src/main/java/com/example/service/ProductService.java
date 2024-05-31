package com.example.service;


import com.example.dto.ProductDto;
import com.example.persistence.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll () ;
    Product findById (Integer id);
    Product createOne (ProductDto productDto) ;
    Product updateOne (ProductDto productDto , Integer id) ;
    List<Product> findByNameContaining (String name) ;

}
