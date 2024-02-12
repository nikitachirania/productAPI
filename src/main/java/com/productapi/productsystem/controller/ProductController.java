package com.productapi.productsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productapi.productsystem.model.ProductModel;
import com.productapi.productsystem.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * add product
	 */

	@PostMapping("/add")
	public String addProduct(@RequestBody ProductModel product) {
		productService.addProduct(product);
		return "succesfully added product";
	}

	/**
	 * get product by id
	 */

	@GetMapping("/get/{id}")
	public ProductModel getCar(@PathVariable(required = true, value = "id") Integer id) {
		return productService.getCar(id);
	}

	/**
	 * update product
	 */

	@PutMapping("/update")
	public String updateProduct(@RequestBody ProductModel updatedProduct) {
		return productService.updateProduct(updatedProduct);
	}

	@DeleteMapping("/delete/{productID}")
	public String deleteProduct(@PathVariable(required = true, value = "productID") Integer productID) {
		return productService.deleteProduct(productID);
	}

	@PostMapping("/apply-discount-or-tax/{productID}")
	public Object applyDiscountOrTax(@PathVariable(required = true, value = "productID") Integer productID, @RequestParam("type") String type,
			@RequestParam("value") double value) {
		return productService.applyDiscountOrTax(productID, type, value);
	}

}
