package com.example.pib2.Products.service.serviceProduct;

import com.example.pib2.Products.model.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long productId);
    ProductDTO createProduct(ProductDTO productDto);
    ProductDTO updateProduct(Long productId, ProductDTO productDto);
    void deleteProduct(Long productId);
}
