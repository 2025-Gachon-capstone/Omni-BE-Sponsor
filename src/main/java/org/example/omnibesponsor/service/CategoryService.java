package org.example.omnibesponsor.service;

import org.example.omnibesponsor.dto.CategoryReqDto;
import org.example.omnibesponsor.dto.CategoryResDto;
import org.example.omnibesponsor.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryReqDto.CreateCategory createCategoryDto);
    List<CategoryResDto.GetCategory> getCategories();
}
