package com.example.pib2.Products.service.serviceImplProduct;

import com.example.pib2.Products.model.Entity.Productos;
import com.example.pib2.Products.model.dto.ProductDTO;
import com.example.pib2.Categories.model.Entity.Categorias;
import com.example.pib2.Products.service.serviceProduct.ProductService;
import com.example.pib2.Products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.getAllWithCategories()
                .stream()
                .map(p -> {
                    ProductDTO dto = new ProductDTO();
                    dto.setProductId(p.getIdProducto());
                    dto.setProducto(p.getProducto());
                    dto.setDescripcion(p.getDescripcion());
                    dto.setIsActive(p.getIsActive());
                    dto.setUnidadId(p.getIdUnidad());
                    dto.setIdCategoria(p.getCategoria().getIdCategoria());
                    dto.setNombreCategoria(p.getCategoria().getCategoria());
                    return dto;
                })
                .toList();
    }

    @Override
    public ProductDTO getProductById(Long productoId) {
        return productRepository.getByIdWithCategories(productoId)
                .map(p -> {
                    ProductDTO dto = new ProductDTO();
                    dto.setProductId(p.getIdProducto());
                    dto.setProducto(p.getProducto());
                    dto.setDescripcion(p.getDescripcion());
                    dto.setIsActive(p.getIsActive());
                    dto.setUnidadId(p.getIdUnidad());
                    dto.setIdCategoria(p.getCategoria().getIdCategoria());
                    dto.setNombreCategoria(p.getCategoria().getCategoria());
                    return dto;
                })
                .orElse(null);
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

        Categorias categoria = new Categorias();
        categoria.setIdCategoria(productDto.getIdCategoria());
        product.setCategoria(categoria);

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
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getIdProducto());
        dto.setProducto(product.getProducto());
        dto.setDescripcion(product.getDescripcion());
        dto.setIsActive(product.getIsActive());
        dto.setUnidadId(product.getIdUnidad());
        dto.setIdCategoria(product.getCategoria().getIdCategoria());
        dto.setNombreCategoria(product.getCategoria().getCategoria());
        return dto;
    }

    private Productos convertToEntity(ProductDTO productDto) {
        Categorias categoria = new Categorias();
        categoria.setIdCategoria(productDto.getIdCategoria());

        return Productos.builder()
                .producto(productDto.getProducto())
                .descripcion(productDto.getDescripcion())
                .idUnidad(productDto.getUnidadId())
                .categoria(categoria)
                .isActive(productDto.getIsActive())
                .build();
    }
}
