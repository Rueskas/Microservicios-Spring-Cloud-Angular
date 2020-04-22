package com.iessanvicente.microservicios.app.usuarios.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iessanvicente.microservicios.app.usuarios.models.services.IAlumnoService;
import com.iessanvicente.microservicios.commons.alumnos.models.entities.Alumno;
import com.iessanvicente.microservicios.commons.controllers.CommonController;

@RestController
public class AlumnoController extends CommonController<Alumno, IAlumnoService> {

	@GetMapping("/por-curso")
	public ResponseEntity<?> obtenerAlumnosPorCursos(@RequestParam List<Long> ids){
		return ResponseEntity.ok(service.findAllById(ids));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Alumno alumno,
			BindingResult result){
		if(result.hasErrors()) {
			return getResponseErrores(result);
		}
		
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
	
	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crear(@Valid Alumno alumno, BindingResult result, @RequestParam MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			alumno.setFoto(file.getBytes());
		}
		return super.crear(alumno, result);
	}
	
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> actualizarConFoto(@PathVariable Long id, @Valid Alumno alumno,
			BindingResult result, @RequestParam MultipartFile file) throws IOException{
		if(result.hasErrors()) {
			return getResponseErrores(result);
		}
		
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
		if(!file.isEmpty()) {
			alumnoEncontrado.setFoto(file.getBytes());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.save(alumnoEncontrado));
	}
	
	@GetMapping("/upload/{id}")
	public ResponseEntity<?> getFoto(@PathVariable Long id){
		Optional<Alumno> o = service.findById(id);
		if(!o.isPresent() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}
		Resource img = new ByteArrayResource(o.get().getFoto());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);
	}
	
}
