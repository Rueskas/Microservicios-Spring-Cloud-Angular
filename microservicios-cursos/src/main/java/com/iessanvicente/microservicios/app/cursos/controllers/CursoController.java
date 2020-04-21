package com.iessanvicente.microservicios.app.cursos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iessanvicente.microservicios.app.cursos.models.entity.Curso;
import com.iessanvicente.microservicios.app.cursos.models.services.ICursoService;
import com.iessanvicente.microservicios.commons.controllers.CommonController;

@RestController
public class CursoController extends CommonController<Curso, ICursoService>{
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Curso curso) {
		Curso cursoDB = service.findById(id).orElse(null);
		if(cursoDB == null) {
			return ResponseEntity.notFound().build();
		}
		cursoDB.setNombre(curso.getNombre() == null? 
				cursoDB.getNombre() : curso.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
}
