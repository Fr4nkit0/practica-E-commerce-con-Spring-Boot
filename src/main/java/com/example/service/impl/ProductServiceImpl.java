package com.example.service.impl;

import com.example.dto.request.SaveProduct;
import com.example.dto.response.GetProduct;
import com.example.exceptions.ResourceNotFoundException;
import com.example.mapper.ProductMapper;
import com.example.persistence.entity.Category;
import com.example.persistence.entity.Product;
import com.example.persistence.repository.CategoryRepository;
import com.example.persistence.repository.ProductRepository;
import com.example.service.ProductService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<GetProduct> findAll() {
        List<Product> entities = productRepository.findAll();
        if (entities.isEmpty()) throw  new ResourceNotFoundException("Registros Vacios");
        return ProductMapper.toGetListDto(entities);
    }

    @Override
    public GetProduct findById(Integer id) {
        Product product = findByIdEntity(id);
        return ProductMapper.toGetDto(product);
    }

    @Override
    public GetProduct createOne(SaveProduct saveProduct) {
        if (!categoryRepository.existsById(saveProduct.categoryId()))throw  new RuntimeException("No existe la categoria") ;
        Product createProduct = ProductMapper.toGetEntity(saveProduct);
        return ProductMapper.toGetDto(productRepository.save(createProduct)) ;
    }

    @Override
    public GetProduct updateOne(SaveProduct updateProduct, Integer id) {
        Product oldProduct = findByIdEntity(id) ;
        ProductMapper.updateEntity(oldProduct,updateProduct);
        return ProductMapper.toGetDto(productRepository.save(oldProduct)) ;
    }

    @Override
    public void deleteOne(Integer id) {
        Product deleteProduct = findByIdEntity(id) ;
        productRepository.delete(deleteProduct);
    }

    private Product findByIdEntity (Integer id){
        return productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe el id:"+id));
    }
}

