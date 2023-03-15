package com.devnoir.dscatalog.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devnoir.dscatalog.dto.ProductDTO;
import com.devnoir.dscatalog.entities.Category;
import com.devnoir.dscatalog.entities.Product;
import com.devnoir.dscatalog.repositories.CategoryRepository;
import com.devnoir.dscatalog.repositories.ProductRepository;
import com.devnoir.dscatalog.services.exceptions.DatabaseException;
import com.devnoir.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devnoir.dscatalog.tests.Factory;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	@InjectMocks
	private ProductService service;
	
	@Mock
	private ProductRepository repository;
	
	@Mock
	private CategoryRepository catRepository;
	
	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private PageImpl<Product> page;
	private Product product;
	private ProductDTO productDTO;
	private Category category;
		
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 100L;
		dependentId = 4L;
		product = Factory.createProduct();
		productDTO = Factory.createProductDTO();
		category = Factory.createCategory();
		page = new PageImpl<>(List.of(product));
		
		when(repository.findAll((Pageable)any())).thenReturn(page);
		when(repository.save(any())).thenReturn(product);
		when(repository.findById(existingId)).thenReturn(Optional.of(product));
		when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		when(repository.find(any(), any(), any())).thenReturn(page);
		when(repository.getOne(existingId)).thenReturn(product);
		when(repository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);
		when(catRepository.getOne(existingId)).thenReturn(category);
		when(catRepository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);
		doNothing().when(repository).deleteById(existingId);
		doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
		doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
	}
	
	@Test
	public void findAllPagedShouldReturnProductDtoPage() {
		 Pageable pageable = PageRequest.of(0, 10);
		 Page<ProductDTO> result = service.findAllPaged(0L, "", pageable);
		 
		 Assertions.assertNotNull(result);
		 verify(repository).findAll(pageable);
	}
	
	@Test
	public void findByIdShouldReturnProductDtoWhenIdExists() {
		ProductDTO dto = service.findById(existingId);
		
		Assertions.assertNotNull(dto);
		verify(repository).findById(existingId);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> service.findById(nonExistingId));
		verify(repository).findById(nonExistingId);
	}
	
	@Test
	public void updateShouldReturnProductDtoWhenIdExists() {
		ProductDTO result = service.update(productDTO, existingId);
		
		Assertions.assertNotNull(result);
		verify(repository).getOne(existingId);
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> service.update(productDTO, nonExistingId));
		verify(repository).getOne(nonExistingId);
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> service.delete(existingId));
		verify(repository, times(1)).deleteById(existingId);;
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete(nonExistingId));
		verify(repository, times(1)).deleteById(nonExistingId);;
	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenIdIsDependent() {
		Assertions.assertThrows(DatabaseException.class, () -> service.delete(dependentId));
		verify(repository, times(1)).deleteById(dependentId);;
	}
}
