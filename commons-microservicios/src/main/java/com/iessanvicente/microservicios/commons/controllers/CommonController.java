package com.iessanvicente.microservicios.commons.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.iessanvicente.microservicios.commons.service.ICommonService;

public class CommonController<E, S extends ICommonService<E>> {
	@Autowired
	protected S service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/page")
	public ResponseEntity<?> listar(Pageable pageable){
		return ResponseEntity.ok(service.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> detalle(@PathVariable Long id){
		E entity = service.findById(id).orElse(null);
		return entity == null? 
				ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody E entity, BindingResult result){
		if(result.hasErrors()) {
			return getResponseErrores(result);
		}
		E entityDB = service.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDB);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	protected ResponseEntity<?> getResponseErrores(BindingResult result){
		Map<String, Object> response = new HashMap<>();
		result.getFieldErrors().forEach(e -> 
			response.put(e.getField(),"El campo "+ e.getField() +" " +  e.getDefaultMessage())
		);
		return ResponseEntity.badRequest().body(response);
	}
}
