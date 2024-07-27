package com.example.mapper;

import com.example.dto.request.SaveProduct;
import com.example.dto.response.GetProduct;
import com.example.persistence.entity.Product;

import java.util.List;

public class ProductMapper {

    public static GetProduct toGetDto (Product entity){
        if (entity==null) return  null ;
        return new GetProduct(
                entity.getName(),
                entity.getDescription(),
                entity.getPrice());
    }
    public static List<GetProduct> toGetListDto (List<Product> entities){
        if (entities==null) return  null;
        return entities.stream().map(ProductMapper::toGetDto).toList();
    }
    public static Product toGetEntity (SaveProduct saveProduct){
        if (saveProduct==null) return  null;
        return Product.builder()
                .price(saveProduct.price())
                .description(saveProduct.description())
                .name(saveProduct.name())
                .categoryId(saveProduct.categoryId())
                .build() ;
    }
    public static void updateEntity (Product oldEntity , SaveProduct updateProduct){
        if (updateProduct== null || oldEntity== null) return;
        oldEntity.setDescription(updateProduct.description());
        oldEntity.setName(updateProduct.name());
        oldEntity.setPrice(updateProduct.price());
        oldEntity.setCategoryId(updateProduct.categoryId());
    }

}
