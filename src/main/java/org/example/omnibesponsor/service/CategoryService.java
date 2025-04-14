package org.example.omnibesponsor.service;

import org.example.omnibesponsor.dto.CategoryReqDto;
import org.example.omnibesponsor.entity.Category;

public interface CategoryService {

    Category createCategory(CategoryReqDto.CreateCategory createCategoryDto);

}
