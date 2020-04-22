package com.iessanvicente.microservicios.app.respuestas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iessanvicente.microservicios.app.respuestas.models.entities.Respuesta;
import com.iessanvicente.microservicios.app.respuestas.models.services.IRespuestaService;

@RestController
public class RespuestaController {

	@Autowired
	private IRespuestaService service;
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody List<Respuesta> respuestas){
		respuestas.forEach(r -> r.setAlumnoId(r.getAlumno().getId()));
		System.out.println(respuestas);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.saveAll(respuestas));
	}
	
	@GetMapping("/alumno/{alumnoId}/examen/{examenId}")
	public ResponseEntity<?> buscarRespuestas(@PathVariable Long alumnoId, @PathVariable Long examenId){
		List<Respuesta> respuestas = service.findByAlumnoByExamen(alumnoId, examenId);

		return ResponseEntity.ok(respuestas);
	}
	
	@GetMapping("/alumno/{alumnoId}/examenes-respondidos")
	public ResponseEntity<?> obtenerExamenesIdConRespuestasPorAlumnoId(
			@PathVariable Long alumnoId){
		List<Long> ids = service.findExamenesIdConRespuestasByAlumnoId(alumnoId);	
		return ResponseEntity.ok(ids);
	}
}
