package com.example.pib2.Categories.Service_imple;

import com.example.pib2.Categories.Model.dto.CategoryDTO;
import com.example.pib2.Categories.Model.Entity.Category;
import com.example.pib2.Categories.Repository.CategoryRepository;
import com.example.pib2.Categories.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setIdCategoria(category.getId_categoria());
        dto.setCategoria(category.getCategoria());
        dto.setActive(category.isActive());
        return dto;
    }

    private Category convertToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId_categoria(dto.getIdCategoria());
        category.setCategoria(dto.getCategoria());
        category.setActive(dto.isActive());
        return category;
    }

    @Override
    public List<CategoryDTO> getAllActiveCategories() {
        return categoryRepository.findAll()
                .stream()
                .filter(Category::isActive)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(this::convertToDTO).orElse(null);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category saved = categoryRepository.save(category);
        return convertToDTO(saved);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    existing.setCategoria(categoryDTO.getCategoria());
                    Category updated = categoryRepository.save(existing);
                    return convertToDTO(updated);
                })
                .orElse(null);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresent(category -> {
            category.setActive(false);
            categoryRepository.save(category);
        });
    }
}
