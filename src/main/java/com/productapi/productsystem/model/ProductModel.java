package com.productapi.productsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private Integer quantityAvailable;

}
