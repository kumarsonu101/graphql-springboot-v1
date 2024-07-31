//package com.kumarsonu101.graphql_service.controller;
//
//import com.kumarsonu101.graphql_service.entity.Product;
//import com.kumarsonu101.graphql_service.repository.ProductRepository;
//import com.kumarsonu101.graphql_service.service.ProductService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.graphql.test.tester.GraphQlTester;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Import({ProductService.class, ProductRepository.class})
//@GraphQlTest(ProductController.class)
//class ProductControllerTest {
//
//    @Autowired
//    GraphQlTester graphQlTester;
//
//
//    @Test
//    void getAllProduct() {
//
//        //language = GraphQl
//        String document = """
//                query{
//                 getAllProduct {
//                     id
//                     name
//                     category
//                     price
//                     stock
//                 }
//                }
//                """;
//
//        graphQlTester.document(document)
//                .execute().path("getAllProduct")
//                .entityList(Product.class).hasSize(1);
//    }
//}