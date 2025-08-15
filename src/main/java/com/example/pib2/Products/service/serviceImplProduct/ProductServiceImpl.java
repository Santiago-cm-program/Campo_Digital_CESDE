package com.example.pib2.Products.service.serviceImplProduct;

import com.example.pib2.Products.model.Entity.Productos;
import com.example.pib2.Products.model.dto.ProductDTO;
import com.example.pib2.Products.service.serviceProduct.ProductService;
import com.example.pib2.Products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Productos product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDto(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDto) {
        Productos product = convertToEntity(productDto);
        Productos savedProduct = productRepository.save(product);
        return convertToDto(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDto) {
        Productos product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setProducto(productDto.getProducto());
        product.setDescripcion(productDto.getDescripcion());
        product.setIdUnidad(productDto.getUnidadId());
        product.setIdCategoria(productDto.getCategoriaId());

        Productos updatedProduct = productRepository.save(product);
        return convertToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        Productos product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setIsActive(false);
        productRepository.save(product);
    }

    private ProductDTO convertToDto(Productos product) {
        return ProductDTO.builder()
                .producto(product.getProducto())
                .descripcion(product.getDescripcion())
                .unidadId(product.getIdUnidad())
                .categoriaId(product.getIdCategoria())
                .build();
    }

    private Productos convertToEntity(ProductDTO productDto) {
        return Productos.builder()
                .producto(productDto.getProducto())
                .descripcion(productDto.getDescripcion())
                .idUnidad(productDto.getUnidadId())
                .idCategoria(productDto.getCategoriaId())
                .build();
    }
}