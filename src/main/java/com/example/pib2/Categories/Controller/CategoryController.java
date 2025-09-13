package com.example.pib2.Categories.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pib2.Categories.Model.dto.CategoryDTO;
import com.example.pib2.Categories.Service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/active")
    @Operation(summary="Obtener todas las categorias activas", description="End Point para obtener todas las categorias activas de la base de datos")
    public ResponseEntity<List<CategoryDTO>> getAllActiveCategories() {
        List<CategoryDTO> categories = categoryService.getAllActiveCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary="Obtener todas las categorias", description="End Point para obtener todas las categorias de la base de datos")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary="Obtener una categoria por ID", description="End Point para obtener una categoria por su ID de la base de datos")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary="Crear una nueva categoria", description="End Point para crear una nueva categoria en la base de datos")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO created = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary="Actualizar una categoria", description="End Point para actualizar una categoria existente en la base de datos")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updated = categoryService.updateCategory(id, categoryDTO);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar una categoria", description="End Point para eliminar una categoria logicamente de la base de datos")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        CategoryDTO existing = categoryService.getCategoryById(id);
        if (existing != null) {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
