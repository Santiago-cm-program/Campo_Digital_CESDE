package com.example.pib2.Products.Service_imple;

import com.example.pib2.Products.Model.dto.ProductDTO;
import com.example.pib2.Products.Model.Entity.Product;
import com.example.pib2.Categories.Model.Entity.Category;
import com.example.pib2.Products.Repository.ProductRepository;
import com.example.pib2.Products.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setIdProducto(product.getId_producto());
        dto.setProducto(product.getProducto());
        dto.setDescripcion(product.getDescripcion());
        dto.setImage(product.getImage());
        dto.setIdUnidad(product.getIdUnidad());
        dto.setIsActive(product.isActive());
        dto.setCategoryId(product.getCategory().getId_categoria());
        dto.setCategoryName(product.getCategory().getCategoria());
        return dto;
    }

    private Product convertToEntity(ProductDTO dto) {
        Product Product = new Product();
        Product.setId_producto(dto.getIdProducto());
        Product.setProducto(dto.getProducto());
        Product.setDescripcion(dto.getDescripcion());
        Product.setImage(dto.getImage());
        Product.setIdUnidad(dto.getIdUnidad());
        Product.setActive(dto.getIsActive());
        Category category = new Category();
        category.setId_categoria(dto.getCategoryId());
        category.setCategoria(dto.getCategoryName());
        Product.setCategory(category);
        return Product;
    }

    @Override
    public List<ProductDTO> getAllActiveProducts() {
        return productRepository.findAll()
                .stream()
                .filter(Product::isActive)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::convertToDTO).orElse(null);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product saved = productRepository.save(product);
        return convertToDTO(saved);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        return productRepository.findById(id)
            .map(existing -> {
                if (productDTO.getProducto() != null) {
                    existing.setProducto(productDTO.getProducto());
                }
                if (productDTO.getDescripcion() != null) {
                    existing.setDescripcion(productDTO.getDescripcion());
                }
                if (productDTO.getImage() != null) {
                    existing.setImage(productDTO.getImage());
                }
                if (productDTO.getIdUnidad() != null) {
                    existing.setIdUnidad(productDTO.getIdUnidad());
                }
                if (productDTO.getIsActive() != null) {
                    existing.setActive(productDTO.getIsActive());
                }
                if (productDTO.getCategoryId() != null) {
                    Category category = new Category();
                    category.setId_categoria(productDTO.getCategoryId());
                    existing.setCategory(category);
                }

                Product updated = productRepository.save(existing);
                return convertToDTO(updated);
            })
            .orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresent(product -> {
            product.setActive(false);
            productRepository.save(product);
        });
    }
}
