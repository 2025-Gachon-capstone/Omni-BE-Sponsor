package org.example.omnibesponsor.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.omnibesponsor.common.apiPayload.ApiResult;
import org.example.omnibesponsor.dto.CategoryReqDto;
import org.example.omnibesponsor.dto.CategoryResDto;
import org.example.omnibesponsor.entity.Category;
import org.example.omnibesponsor.repository.CategoryRepository;
import org.example.omnibesponsor.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sponsor/v1")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
       this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    @Operation(summary = "카테고리 생성 API",description = "카테고리 생성 API 입니다. 중복은 안됩니다.", tags = "Category")
    public ApiResult<CategoryResDto.CreateCategory> createCategory(@RequestBody CategoryReqDto.CreateCategory createCategoryDto){

        Category savedCategory = categoryService.createCategory(createCategoryDto);
        return ApiResult.onSuccess(new CategoryResDto.CreateCategory(savedCategory.getCategoryId(),savedCategory.getTitle()));

    }

}
