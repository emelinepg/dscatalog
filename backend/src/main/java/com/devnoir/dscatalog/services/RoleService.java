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

import com.devnoir.dscatalog.dto.RoleDTO;
import com.devnoir.dscatalog.entities.Role;
import com.devnoir.dscatalog.repositories.RoleRepository;
import com.devnoir.dscatalog.services.exceptions.DatabaseException;
import com.devnoir.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	@Transactional(readOnly = true)
	public Page<RoleDTO> findAllPaged(Pageable pageable) {
		Page<Role> list = repository.findAll(pageable);
		return list.map(role -> new RoleDTO(role));
	}
	
	@Transactional(readOnly = true) 
	public RoleDTO findById(Long id) {
		Optional<Role> opt = repository.findById(id);
		Role role = opt.orElseThrow(() -> new ResourceNotFoundException("Entity not found")); 
		return new RoleDTO(role);
	}
	
	@Transactional
	public RoleDTO insert(RoleDTO dto) {
		Role role = new Role();
		role.setAuthority(dto.getAuthority());
		role = repository.save(role);
		return new RoleDTO(role);
	}
	
	@Transactional
	public RoleDTO update(RoleDTO dto, Long id) {
		try {
			Role role = repository.getOne(id);
			role.setAuthority(dto.getAuthority()); 
			role = repository.save(role);
			return new RoleDTO(role);
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
