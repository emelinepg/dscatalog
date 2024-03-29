package com.devnoir.dscatalog.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnoir.dscatalog.dto.CategoryDTO;
import com.devnoir.dscatalog.dto.ProductDTO;
import com.devnoir.dscatalog.entities.Category;
import com.devnoir.dscatalog.entities.Product;
import com.devnoir.dscatalog.repositories.CategoryRepository;
import com.devnoir.dscatalog.repositories.ProductRepository;
import com.devnoir.dscatalog.services.exceptions.DatabaseException;
import com.devnoir.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(Long categoryId, String name, Pageable pageable) {
		List<Category> categories = (categoryId == 0) ? null : Arrays.asList(categoryRepository.getOne(categoryId));
		Page<Product> page = repository.find(categories, name, pageable);
		repository.findProductsWithCategories(page.getContent());
		return page.map(x -> new ProductDTO(x, x.getCategories()));
	}
	
	@Transactional(readOnly = true) 
	public ProductDTO findById(Long id) {
		Optional<Product> opt = repository.findById(id);
		Product prod = opt.orElseThrow(() -> new ResourceNotFoundException("Entity not found")); 
		return new ProductDTO(prod, prod.getCategories());
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product prod = new Product();
		copyDtoToEntity(dto, prod);
		prod = repository.save(prod);
		return new ProductDTO(prod);
	}
	
	@Transactional
	public ProductDTO update(ProductDTO dto, Long id) {
		try {
			Product prod = repository.getOne(id);
			copyDtoToEntity(dto, prod);
			prod = repository.save(prod);
			return new ProductDTO(prod);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void copyDtoToEntity(ProductDTO dto, Product prod) {
		prod.setName(dto.getName());
		prod.setDescription(dto.getDescription());
		prod.setDate(dto.getDate());
		prod.setPrice(dto.getPrice());
		prod.setImgUrl(dto.getImgUrl());
		
		prod.getCategories().clear();
		for(CategoryDTO catDto :dto.getCategories()) {
			Category cat = categoryRepository.getOne(catDto.getId());
			prod.getCategories().add(cat);
		}
	}
}
