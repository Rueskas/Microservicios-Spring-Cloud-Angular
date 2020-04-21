package com.iessanvicente.microservicios.app.usuarios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iessanvicente.microservicios.app.usuarios.models.entity.Alumno;
import com.iessanvicente.microservicios.app.usuarios.models.services.IAlumnoService;

@RestController
public class AlumnoController {
	@Autowired
	private IAlumnoService alumnoService;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(alumnoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> detalle(@PathVariable Long id){
		Alumno alumno = alumnoService.findById(id).orElse(null);
		return alumno == null? 
				ResponseEntity.notFound().build() : ResponseEntity.ok(alumno);
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Alumno alumno){
		Alumno alumnoGuardado = alumnoService.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoGuardado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Alumno alumno){
		Alumno alumnoEncontrado = alumnoService.findById(id).orElse(null);
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
				.body(alumnoService.save(alumnoEncontrado));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		alumnoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
