package com.example.service;


import com.example.dto.request.SaveProduct;
import com.example.dto.response.GetProduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    Page<GetProduct> findAll (Pageable pageable) ;
    GetProduct findById (Integer id);
    GetProduct createOne (SaveProduct saveProduct) ;
    GetProduct updateOne (SaveProduct updateProduct, Integer id) ;

}
