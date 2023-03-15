package com.devnoir.dscatalog.services;

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
import com.devnoir.dscatalog.entities.Category;
import com.devnoir.dscatalog.repositories.CategoryRepository;
import com.devnoir.dscatalog.services.exceptions.DatabaseException;
import com.devnoir.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(Pageable pageable) {
		Page<Category> list = repository.findAll(pageable);
		return list.map(cat -> new CategoryDTO(cat));
	}
	
	@Transactional(readOnly = true) 
	public CategoryDTO findById(Long id) {
		Optional<Category> opt = repository.findById(id);
		Category cat = opt.orElseThrow(() -> new ResourceNotFoundException("Entity not found")); 
		return new CategoryDTO(cat);
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category cat = new Category();
		cat.setName(dto.getName());
		cat = repository.save(cat);
		return new CategoryDTO(cat);
	}
	
	@Transactional
	public CategoryDTO update(CategoryDTO dto, Long id) {
		try {
			Category cat = repository.getOne(id);
			cat.setName(dto.getName());
			cat = repository.save(cat);
			return new CategoryDTO(cat);
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


}
