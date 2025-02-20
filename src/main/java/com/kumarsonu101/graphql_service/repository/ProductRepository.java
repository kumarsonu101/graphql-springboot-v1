package com.kumarsonu101.graphql_service.repository;

import com.kumarsonu101.graphql_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findByCategory(String category);
}
