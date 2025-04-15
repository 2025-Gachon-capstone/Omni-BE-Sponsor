package org.example.omnibesponsor.converter;

import org.example.omnibesponsor.dto.CategoryReqDto;
import org.example.omnibesponsor.dto.CategoryResDto;
import org.example.omnibesponsor.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryConverter {

    // Dto -> Entity
    public static Category toCategory(CategoryReqDto.CreateCategory dto) {
        return Category.builder()
                .title(dto.getTitle())
                .build();
    }


    // Entity -> Dto
    public static CategoryResDto.GetCategory toGetCategoryDto(Category category) {
        return CategoryResDto.GetCategory.builder()
                .categoryId(category.getCategoryId())
                .title(category.getTitle())
                .build();
    }

    public static List<CategoryResDto.GetCategory> toGetCategoryDto(List<Category> categories) {
        return categories.stream()
                .map(CategoryConverter::toGetCategoryDto)
                .collect(Collectors.toList());
    }

}
