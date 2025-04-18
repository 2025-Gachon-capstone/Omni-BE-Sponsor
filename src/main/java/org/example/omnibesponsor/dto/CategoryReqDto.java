package org.example.omnibesponsor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class CategoryReqDto {

    @Getter
    public static class CreateCategory{

        @NotBlank(message = "카테고리명은 필수입니다.")
        private String title;

    }
}
