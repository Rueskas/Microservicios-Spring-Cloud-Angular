package com.iessanvicente.microservicios.app.examenes.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iessanvicente.microservicios.app.examenes.models.services.IExamenService;
import com.iessanvicente.microservicios.commons.controllers.CommonController;
import com.iessanvicente.microservicios.commons.examenes.models.entities.Examen;

@RestController
public class ExamenController extends CommonController<Examen, IExamenService>{
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Examen examen) {
		Optional<Examen> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Examen examenDB = o.get();
		examenDB.setNombre(examen.getNombre() != null?
				examen.getNombre() : examenDB.getNombre());

		examenDB.getPreguntas().stream()
			.filter(p-> !examen.getPreguntas().contains(p))
			.collect(Collectors.toList())
			.forEach(examenDB::removePregunta);
		
		examenDB.setPreguntas(examen.getPreguntas());
		examenDB.setAsignatura(examen.getAsignatura() != null?
				examen.getAsignatura() : examenDB.getAsignatura());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDB));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		List<Examen> examenes = service.findByNombre(term);
		if(examenes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(examenes);
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas(){
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
}
