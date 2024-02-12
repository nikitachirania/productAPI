package com.productapi.productsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.productapi.productsystem.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {

}
