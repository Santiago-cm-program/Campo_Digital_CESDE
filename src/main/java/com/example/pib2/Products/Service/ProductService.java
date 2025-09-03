package com.example.pib2.Products.Service;

import com.example.pib2.Products.Model.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllActiveProducts();
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
