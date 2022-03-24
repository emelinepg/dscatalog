package com.devnoir.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnoir.dscatalog.dto.CategoryDTO;
import com.devnoir.dscatalog.entities.Category;
import com.devnoir.dscatalog.repositories.CategoryRepository;

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
	
	public Optional<Category> findById(Long id) {
		return repository.findById(id);
	}
}
