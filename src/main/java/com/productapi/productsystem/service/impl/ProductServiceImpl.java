package com.productapi.productsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.productapi.productsystem.model.ProductModel;
import com.productapi.productsystem.repository.ProductRepository;
import com.productapi.productsystem.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Object addProduct(ProductModel product) {
		try {
			//Add new product to the database
			ProductModel savedProduct = productRepository.save(product);
			return savedProduct;
		} catch (Exception e) {
			// Handle the exception and return an error message
			return "Error creating product: " + e.getMessage();
		}
	}

	@Override
	public ProductModel getCar(Integer id) {
		//Get product by id if exists else throw exception
		ProductModel product = productRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid product Id:" + id));

		return product;
	}

	@Override
	public String updateProduct(ProductModel updatedProduct) {
		if (productRepository.existsById(updatedProduct.getId())) {
			// Save the updated product to the database
			productRepository.save(updatedProduct);
			return "Product updated successfully";
		} else {
			return "Error: Product with ID " + updatedProduct.getId() + " not found. Update failed.";
		}
	}

	@Override
	public String deleteProduct(int productID) {
		try {
			// Check if the product exists
			if (productRepository.existsById(productID)) {
				// Delete the product from the database
				productRepository.deleteById(productID);
				return "Product deleted successfully";
			} else {
				return "Error: Product with ID " + productID + " not found. Deletion failed.";
			}
		} catch (Exception e) {
			// Handle the exception and return an error message
			return "Error deleting product: " + e.getMessage();
		}
	}

	@Override
	public Object applyDiscountOrTax(int productID, String type, double value) {
		try {
			// Retrieve the product from the database
			ProductModel product = productRepository.findById(productID).orElse(null);

			// Check if the product exists
			if (product != null) {
				// Apply either a discount or tax 
				if ("discount".equalsIgnoreCase(type)) {
					product.setPrice(product.getPrice() * (1 - value / 100));
				} else if ("tax".equalsIgnoreCase(type)) {
					product.setPrice(product.getPrice() * (1 + value / 100));
				} else {
					return "Error: Invalid type provided. Use 'discount' or 'tax'.";
				}
				// Save the updated product to the database
				productRepository.save(product);


				return product;
			} else {
				return "Error: Product with ID " + productID + " not found. Operation failed.";
			}
		} catch (Exception e) {
			// Handle the exception and return an error message
			return "Error applying discount or tax: " + e.getMessage();
		}
	}
}
