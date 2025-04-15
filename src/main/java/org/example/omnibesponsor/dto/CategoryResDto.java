package org.example.omnibesponsor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.omnibesponsor.entity.Category;

public class CategoryResDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateCategory {

        private Long categoryId;
        private String title;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetCategory{

        private Long categoryId;
        private String title;

    }

}
