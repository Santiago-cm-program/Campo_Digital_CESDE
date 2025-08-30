package com.example.pib2.Products.service.serviceProduct;

import com.example.pib2.Products.model.dto.ProductDTO;
import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long productId);
    ProductDTO createProduct(ProductDTO productDto);
    ProductDTO updateProduct(Long productId, Map<String, Object> updates);
    void deleteProduct(Long productId);
}
