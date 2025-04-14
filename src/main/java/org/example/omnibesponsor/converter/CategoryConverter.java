package org.example.omnibesponsor.converter;

import org.example.omnibesponsor.dto.CategoryReqDto;
import org.example.omnibesponsor.entity.Category;

public class CategoryConverter {

    public static Category toCategory(CategoryReqDto.CreateCategory dto) {
        return Category.builder()
                .title(dto.getTitle())
                .build();
    }

}
