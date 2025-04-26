package org.example.omnibesponsor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class ProductCategoryReqDto {

    @Getter
    public static class CreateProductCategory{

        @NotBlank(message = "카테고리 이름은 필수입니다.")
        public String name;

    }

}
