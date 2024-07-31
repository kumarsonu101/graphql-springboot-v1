package com.kumarsonu101.graphql_service.service;

import com.kumarsonu101.graphql_service.entity.InputProduct;
import com.kumarsonu101.graphql_service.entity.Product;
import com.kumarsonu101.graphql_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

//    public Product updateStock(int id, int stockQuantity) {
//        System.out.println("inside service method");
//        Product existingProduct =
//                productRepository.findById(id)
//                        .orElseThrow(() -> new RuntimeException("Product not found for given id" + id));
//        existingProduct.setStock(stockQuantity);
//        return productRepository.save(existingProduct);
//    }

    public Product updateStock(int id, int stock){

        Product existingProduct= productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("product not found with id "+id));

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

    public Product addNewProduct(InputProduct inputProduct) {
        Product newProduct = new Product();
        newProduct.setStock(inputProduct.getStock());
        newProduct.setName(inputProduct.getName());
        newProduct.setPrice(inputProduct.getPrice());
        newProduct.setStock(inputProduct.getStock());
       return productRepository.save(newProduct);
    }
}
