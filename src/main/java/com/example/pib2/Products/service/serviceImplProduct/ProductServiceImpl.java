package com.example.pib2.Products.service.serviceImplProduct;

import com.example.pib2.Products.model.Entity.Productos;
import com.example.pib2.Products.model.dto.ProductDTO;
import com.example.pib2.Categories.model.Entity.Categorias;
import com.example.pib2.Categories.repository.CategoryRepository;
import com.example.pib2.Products.service.serviceProduct.ProductService;
import com.example.pib2.Products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

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
    public ProductDTO updateProduct(Long productId, Map<String, Object> updates) {
    Productos product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        updates.forEach((campo, valor) -> {
            switch (campo) {
                case "producto":
                    product.setProducto((String) valor);
                    break;
                case "descripcion":
                    product.setDescripcion((String) valor);
                    break;
                case "isActive":
                    product.setIsActive(Boolean.valueOf(valor.toString()));
                    break;
                case "unidadId":
                    product.setIdUnidad(Integer.valueOf(valor.toString()));
                    break;
                case "categoriaId":
                    Categorias categoria = categoryRepository.findById(Long.valueOf(valor.toString()))
                            .orElseThrow(() -> new RuntimeException("Category not found"));
                    product.setCategoria(categoria);
                    break;
                default:
                    throw new IllegalArgumentException("Campo no soportado: " + campo);
        }
    });

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
