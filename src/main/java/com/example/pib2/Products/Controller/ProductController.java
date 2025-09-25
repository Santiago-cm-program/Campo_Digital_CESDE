package com.example.pib2.Products.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.pib2.Products.Model.dto.ProductDTO;
import com.example.pib2.Products.Service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") 
@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/GET/active")
    @Operation(summary="Obtener todos los productos activos", description="End Point para obtener todos los productos activos de la base de datos")
    public ResponseEntity<List<ProductDTO>> getAllActiveProducts() {
        List<ProductDTO> products = productService.getAllActiveProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/GET/all")
    @Operation(summary="Obtener todos los productos", description="End Point para obtener todos los productos de la base de datos")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/GET/{id}")
    @Operation(summary="Obtener un producto por ID", description="End Point para obtener un producto por su ID de la base de datos")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/POST")
    @Operation(summary="Crear un nuevo producto", description="End Point para crear un nuevo producto en la base de datos")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO created = productService.createProduct(productDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/PATCH/{id}")
    @Operation(summary="Actualizar un producto", description="End Point para actualizar parcialmente un producto existente en la base de datos")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updated = productService.updateProduct(id, productDTO);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/DELETE/{id}")
    @Operation(summary="Eliminar un producto", description="End Point para eliminar un producto logicamente de la base de datos")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        ProductDTO existing = productService.getProductById(id);
        if (existing != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
