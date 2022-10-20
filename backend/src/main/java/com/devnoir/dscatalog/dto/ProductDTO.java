package com.devnoir.dscatalog.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.devnoir.dscatalog.entities.Category;
import com.devnoir.dscatalog.entities.Product;

public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@Size(min = 5, max = 60)
	private String name;
	private String description;
	@Positive
	private Double price;
	private String imgUrl;
	@PastOrPresent
	private Instant date;
	private List<CategoryDTO> categories = new ArrayList<>();
	
	
	public ProductDTO() {
	}

	public ProductDTO(Long id, String name, String description, Instant date, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.date = date;
	}
	
	public ProductDTO(Product product) {
		id = product.getId();
		name = product.getName();
		description = product.getDescription();
		price = product.getPrice();
		imgUrl = product.getImgUrl();
		date = product.getDate();
	}
	
	public ProductDTO(Product product, Set<Category> categories) {
		this(product);
		categories.forEach(x -> this.categories.add(new CategoryDTO(x)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}
}
