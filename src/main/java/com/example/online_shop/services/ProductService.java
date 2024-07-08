package com.example.online_shop.services;

import com.example.online_shop.dto.ProductDto;
import com.example.online_shop.models.Product;
import com.example.online_shop.models.User;
import com.example.online_shop.repositories.CategoryRepository;
import com.example.online_shop.repositories.ProductRepository;
import com.example.online_shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    public void create(ProductDto productDto) throws Exception {
        // Check if a product with the same name and category already exists
        if (productRepository.existsByNameAndCategory_Name(productDto.getName(), productDto.getCategory().getName())) {
            throw new Exception("Product is already in sell");
        }

        // Map ProductDto to Product entity
        Product product = new Product();
        product.setName(productDto.getName());
        product.setImagePath(productDto.getImagePath());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCategory(productDto.getCategory());
        product.setSeller(productDto.getSeller());
        product.setInventory(productDto.getStock());
        product.setDescription(productDto.getDescription());

        productRepository.save(product);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setImagePath(product.getImagePath());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setCategory(product.getCategory());
        productDto.setSeller(product.getSeller());
        productDto.setInventory(product.getInventory());
        productDto.setDescription(product.getDescription());
        return productDto;
    }

    public void deleteProduct(Product entity) {
        productRepository.delete(entity);
    }

    @Transactional
    public void deleteProductsBySeller(User seller) {
        productRepository.deleteBySeller(seller);
    }

    public List<Product> findProductsBySellerId(int sellerId) {
        return productRepository.findBySeller_Id(sellerId);
    }

    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    public Product findById(int id) {
        return productRepository.findById(id);
    }

    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(ProductDto productDto) {
        Product existingProduct = productRepository.findByName(productDto.getName());

        int updatedRows = productRepository.updateProduct(
                existingProduct.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getStock(),
                productDto.getInventory(),
                productDto.getCategory());
    }

    public List<Product> findLatestProducts() {
        return productRepository.findTop3ByOrderByCreatedAtDesc();
    }

    public long getProductCount() {
        return productRepository.countProducts();
    }

    public int countProductsBySeller(User seller) {
        return productRepository.countBySeller(seller);
    }

    public void updateInventory(Product product, int quantity) {
        product.setInventory(product.getInventory() - quantity);
        productRepository.save(product);
    }
}

