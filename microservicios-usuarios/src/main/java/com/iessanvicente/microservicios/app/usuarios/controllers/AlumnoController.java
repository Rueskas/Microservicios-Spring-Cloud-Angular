package com.iessanvicente.microservicios.app.usuarios.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iessanvicente.microservicios.app.usuarios.models.services.IAlumnoService;
import com.iessanvicente.microservicios.commons.alumnos.models.entities.Alumno;
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
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		List<Alumno> alumnos = service.findByNombreOrApellido(term);
		if(alumnos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(alumnos);
		
	}
	
}
