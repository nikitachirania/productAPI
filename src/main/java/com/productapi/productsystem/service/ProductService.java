package com.productapi.productsystem.service;

import com.productapi.productsystem.model.ProductModel;

public interface ProductService {

	Object addProduct(ProductModel product);

	ProductModel getCar(Integer id);

	String updateProduct(ProductModel updatedProduct);

	String deleteProduct(int productID);

	Object applyDiscountOrTax(int productID, String type, double value);

}
