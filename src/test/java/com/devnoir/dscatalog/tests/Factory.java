package com.devnoir.dscatalog.tests;

import java.time.Instant;

import com.devnoir.dscatalog.dto.ProductDTO;
import com.devnoir.dscatalog.entities.Category;
import com.devnoir.dscatalog.entities.Product;

public class Factory {

	public static Product createProduct() {
		Product product = new Product(1L, "Phone", "Good Phone", Instant.parse("2020-10-20T03:00:00Z"), 800.0, "https://img.com/img.png");
		product.getCategories().add(new Category(2L, "Electronics"));
		return product;
	}
	
	public static ProductDTO createProductDTO() {
		Product product = createProduct();
		return new ProductDTO(product, product.getCategories());
	}
}
