package com.productapi.productsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.productapi.productsystem.model.ProductModel;
import com.productapi.productsystem.repository.ProductRepository;
import com.productapi.productsystem.service.impl.ProductServiceImpl;

@SpringBootTest
class ProductapiApplicationTests {
	
	 @Mock
	    private ProductRepository productRepository;

	    @InjectMocks
	    private ProductServiceImpl productService;

	    @Test
	    public void testApplyDiscount() {
	        // Mock data
	        Integer productId = 1;
	        double discountPercentage = 10.0;
	        ProductModel product = new ProductModel();
	        product.setId(productId);
	        product.setPrice(30.0);

	        // Mock repository behavior
	        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(product));

	        // Call the service method
	        Object result = productService.applyDiscountOrTax(productId, "discount", discountPercentage);
	        
	        // Verify the result
	        assertEquals(product, result);
	        assertEquals(27.0, product.getPrice(), 0.01);
	    }
	    
	    @Test
	    public void testApplyTax() {
	        // Mock data
	        Integer productId = 2;
	        double discountPercentage = 10.0;
	        ProductModel product = new ProductModel();
	        product.setId(productId);
	        product.setPrice(30.0);

	        // Mock repository behavior
	        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(product));

	        // Call the service method
	        Object result = productService.applyDiscountOrTax(productId, "tax", discountPercentage);

	        // Verify the result
	        assertEquals(product, result);
	        assertEquals(33.0, product.getPrice(), 0.01);
	    }


}
