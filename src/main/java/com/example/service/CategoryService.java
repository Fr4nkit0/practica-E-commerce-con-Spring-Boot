package com.example.service;

import com.example.dto.request.SaveCategory;
import com.example.dto.response.GetCategory;
import com.example.dto.response.GetCategoryStatistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<GetCategory> findALl (Pageable pageable);
    GetCategoryStatistic findById (Integer id) ;
    GetCategory createOne (SaveCategory saveCategory);
    GetCategory updateById (SaveCategory updateCategory,Integer id);
    GetCategory disableOneById (Integer id);
}