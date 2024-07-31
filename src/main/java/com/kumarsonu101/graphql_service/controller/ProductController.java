package com.kumarsonu101.graphql_service.controller;

import com.kumarsonu101.graphql_service.entity.InputProduct;
import com.kumarsonu101.graphql_service.entity.Product;
import com.kumarsonu101.graphql_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @QueryMapping
    public List<Product> getAllProduct() {
        return productService.getProducts();
    }

    @QueryMapping
    public List<Product> getAllProductByCategory(@Argument String category) {
        return productService.getProductsByCategory(category);
    }

    //update stock by id
    @MutationMapping
    public Product updateStockById(@Argument int id, @Argument int stock) {
        Product updatedProduct = productService.updateStock(id, stock);
        return updatedProduct;
    }

    @MutationMapping
    public Product updateNewStockWithExistingStock(@Argument int id, @Argument int newStockQuantity) {
        return productService.addNewStockWithExistingStock(id, newStockQuantity);
    }

    @MutationMapping
    public Product addProduct(InputProduct inputProduct) {
        return productService.addNewProduct(inputProduct);
    }

}
