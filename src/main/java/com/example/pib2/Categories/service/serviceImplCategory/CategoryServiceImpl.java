package com.example.pib2.Categories.service.serviceImplCategory;

import com.example.pib2.Categories.model.dto.CategoryDTO;
import com.example.pib2.Categories.model.Entity.Categorias;
import com.example.pib2.Categories.repository.CategoryRepository;
import com.example.pib2.Categories.service.serviceCategory.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getAllCategories() {
    return categoryRepository.findAll()
            .stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
}

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        Categorias category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoria not found"));
        return convertToDto(category);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDto) {
        Categorias category = convertToEntity(categoryDto);
        Categorias savedCategory = categoryRepository.save(category);
        return convertToDto(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDto) {
        Categorias category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoria not found"));

        category.setCategoria(categoryDto.getCategory());
        category.setActive(categoryDto.isActive());
        Categorias updatedCategory = categoryRepository.save(category);
        return convertToDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Categorias category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setActive(false);
        categoryRepository.save(category);
    }

    private CategoryDTO convertToDto(Categorias category) {
        return CategoryDTO.builder()
                .categoryId(category.getIdCategoria())
                .category(category.getCategoria())
                .isActive(category.isActive())
                .build();
    }

    private Categorias convertToEntity(CategoryDTO categoryDto) {
        return Categorias.builder()
                .categoria(categoryDto.getCategory())
                .isActive(categoryDto.isActive())
                .build();
    }
}
