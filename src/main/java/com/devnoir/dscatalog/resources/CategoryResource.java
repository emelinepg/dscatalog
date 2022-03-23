package com.devnoir.dscatalog.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devnoir.dscatalog.dto.CategoryDTO;
import com.devnoir.dscatalog.entities.Category;
import com.devnoir.dscatalog.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<CategoryDTO> list = new ArrayList<>();
		Category cat1 = new Category(1L, "books");
		Category cat2 = new Category(2L, "computers");
		list.addAll(Arrays.asList(new CategoryDTO(cat1), new CategoryDTO(cat2)));
		return ResponseEntity.ok().body(list);
	}
	
}
