package org.example.omnibesponsor.service;

import org.example.omnibesponsor.common.apiPayload.code.status.ErrorStatus;
import org.example.omnibesponsor.common.apiPayload.exception.GeneralException;
import org.example.omnibesponsor.converter.CategoryConverter;
import org.example.omnibesponsor.dto.CategoryReqDto;
import org.example.omnibesponsor.dto.CategoryResDto;
import org.example.omnibesponsor.entity.Category;
import org.example.omnibesponsor.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Category createCategory(CategoryReqDto.CreateCategory createCategoryDto) {

        if(categoryRepository.existsByTitle(createCategoryDto.getTitle())){
            throw new GeneralException(ErrorStatus._ALREADY_EXIST_CATEGORY);
        }

        Category category = CategoryConverter.toCategory(createCategoryDto);

        Category savedCategory = categoryRepository.save(category);

        return savedCategory;
    }

    @Override
    public List<CategoryResDto.GetCategory> getCategories() {

        List<Category> categories = categoryRepository.findAll();

        return CategoryConverter.toGetCategoryDto(categories);
    }

}
