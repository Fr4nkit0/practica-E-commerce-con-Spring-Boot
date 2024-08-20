package com.example.mapper;

import com.example.dto.request.SaveCategory;
import com.example.dto.response.GetCategory;
import com.example.dto.response.GetCategoryStatistic;
import com.example.persistence.entity.Category;
import com.example.persistence.util.Status;


public class CategoryMapper {
    public static GetCategoryStatistic toGetCategoryStatistic (Category entity,
                                                               int totalProducts,
                                                               Double averagePrice,
                                                               int lowestPrice,
                                                               int highestPrice){

        if (entity==null) return null;
        return new GetCategoryStatistic(entity.getName(),
                totalProducts,
                averagePrice,
                lowestPrice,
                highestPrice
                );
    }
    public  static GetCategory toGetDto (Category entity){
        if (entity==null) return null;
        return new GetCategory(entity.getName());
    }
    public  static Category toGetEntity (SaveCategory saveCategory){
        if (saveCategory==null) return null;
        return  Category.builder()
                .name(saveCategory.name())
                .status(Status.ENABLED)
                .build();
    }
    public  static void updateEntity (SaveCategory updateCategory , Category oldCategory){
        if (updateCategory==null) return;
        oldCategory.setName(updateCategory.name());
    }






}
