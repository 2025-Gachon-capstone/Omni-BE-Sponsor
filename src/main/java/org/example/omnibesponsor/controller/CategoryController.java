package org.example.omnibesponsor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.omnibesponsor.common.apiPayload.ApiResult;
import org.example.omnibesponsor.dto.CategoryReqDto;
import org.example.omnibesponsor.dto.CategoryResDto;
import org.example.omnibesponsor.entity.Category;
import org.example.omnibesponsor.repository.CategoryRepository;
import org.example.omnibesponsor.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sponsor/v1")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
       this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    @Operation(summary = "카테고리 생성 API",description = "카테고리 생성 API 입니다. 중복은 안됩니다. - ( 사용 안함 x )", tags = "Category")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "COMMON200-성공",content = @Content(schema = @Schema(implementation = ApiResult.class))),
            @ApiResponse(responseCode = "4001", description = "CATEGORY4001-이미 존재하는 카테고리입니다.",content = @Content(schema = @Schema(implementation = ApiResult.class))),
    })
    public ApiResult<CategoryResDto.CreateCategory> createCategory(@Valid @RequestBody CategoryReqDto.CreateCategory createCategoryDto){

        Category savedCategory = categoryService.createCategory(createCategoryDto);
        return ApiResult.onSuccess(new CategoryResDto.CreateCategory(savedCategory.getCategoryId(),savedCategory.getTitle()));

    }

    // 인증 x 화이트 리스트에 추가하기
    @GetMapping("/categories")
    @Operation(summary = "카테고리 가져오기 API",description = "회원가입시 필요한 카테고리 목록 가져오기 입니다. ( 인증 필요없음 )", tags = "Category")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "COMMON200-성공",content = @Content(schema = @Schema(implementation = ApiResult.class))),
    })
    public ApiResult<List<CategoryResDto.GetCategory>> getCategories(){

        return ApiResult.onSuccess(categoryService.getCategories());

    }


}
