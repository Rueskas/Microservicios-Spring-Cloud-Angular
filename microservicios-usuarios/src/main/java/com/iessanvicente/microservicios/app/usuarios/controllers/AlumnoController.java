package com.iessanvicente.microservicios.app.usuarios.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iessanvicente.microservicios.app.usuarios.models.entity.Alumno;
import com.iessanvicente.microservicios.app.usuarios.models.services.IAlumnoService;
import com.iessanvicente.microservicios.commons.controllers.CommonController;

@RestController
public class AlumnoController extends CommonController<Alumno, IAlumnoService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Alumno alumno){
		Alumno alumnoEncontrado = service.findById(id).orElse(null);
		if(alumnoEncontrado == null) {
			return ResponseEntity.notFound().build();
		}
		
		alumnoEncontrado.setNombre(alumno.getNombre() != null?
				alumno.getNombre() : alumnoEncontrado.getNombre());
		alumnoEncontrado.setApellidos(alumno.getApellidos() != null?
				alumno.getApellidos() : alumnoEncontrado.getApellidos());
		alumnoEncontrado.setEmail(alumno.getEmail() != null?
				alumno.getEmail() : alumnoEncontrado.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.save(alumnoEncontrado));
	}
	
}
