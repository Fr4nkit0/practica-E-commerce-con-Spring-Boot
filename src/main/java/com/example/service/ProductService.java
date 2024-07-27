package com.example.service;


import com.example.dto.request.SaveProduct;
import com.example.dto.response.GetProduct;
import com.example.persistence.entity.Product;

import java.util.List;

public interface ProductService {
    List<GetProduct> findAll () ;
    GetProduct findById (Integer id);
    GetProduct createOne (SaveProduct saveProduct) ;
    GetProduct updateOne (SaveProduct updateProduct, Integer id) ;
    void deleteOne (Integer id);
}
