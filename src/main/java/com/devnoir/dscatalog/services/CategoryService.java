package com.devnoir.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnoir.dscatalog.dto.CategoryDTO;
import com.devnoir.dscatalog.entities.Category;
import com.devnoir.dscatalog.repositories.CategoryRepository;
import com.devnoir.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		
		/*List<CategoryDTO> categories = new ArrayList<>();
		for (Category x : list) {
			CategoryDTO dto = new CategoryDTO(x);
			categories.add(dto);
		}*/
		
		// é possível fazer o mesmo com uma expressão lambda
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true) 
	public CategoryDTO findById(Long id) {
		Optional<Category> opt = repository.findById(id);
		Category cat = opt.orElseThrow(() -> new ResourceNotFoundException("Entity not found")); 
		return new CategoryDTO(cat);
		
		// outra maneira de fazer (fiz e deu certo):
		// return new CategoryDTO(cat.get().getId(), cat.get().getName());
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
			Category cat = repository.getById(id);
			cat.setName(dto.getName());
			cat = repository.save(cat);
			return new CategoryDTO(cat);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		}
	}
}
