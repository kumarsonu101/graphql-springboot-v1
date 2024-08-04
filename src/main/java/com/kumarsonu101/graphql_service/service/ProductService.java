package com.kumarsonu101.graphql_service.service;

import com.kumarsonu101.graphql_service.Exception.ProductNotFoundException;
import com.kumarsonu101.graphql_service.entity.InputProduct;
import com.kumarsonu101.graphql_service.entity.Product;
import com.kumarsonu101.graphql_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    public Product updateStock(int id, int stock) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found with id " + id));

        existingProduct.setStock(stock);
        return productRepository.save(existingProduct);
    }


    /*
    This will update count of stocks for existing stock by combining new and old stock
     */
    public Product addNewStockWithExistingStock(int id, int newStockQuantity) {
        Product existingProduct =
                productRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Product not found for given id" + id));
        existingProduct.setStock(newStockQuantity + existingProduct.getStock());
        return productRepository.save(existingProduct);
    }

    public String deleteProductById(int id) {
        productRepository.findById(id)
                .ifPresentOrElse(
                        product -> productRepository.delete(product),
                        () -> {
                            throw new ProductNotFoundException("Product not found with id: " + id);
                        }
                );
        return "Product deleted successfully";
    }

    public Product addNewProduct(String category, String name, Float price, int stock) {
        Product newProduct = new Product(category,name,price,stock);
        return productRepository.save(newProduct);
    }
}
