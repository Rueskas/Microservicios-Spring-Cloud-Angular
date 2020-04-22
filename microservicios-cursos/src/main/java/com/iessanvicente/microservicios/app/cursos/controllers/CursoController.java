package com.iessanvicente.microservicios.app.cursos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iessanvicente.microservicios.app.cursos.models.entity.Curso;
import com.iessanvicente.microservicios.app.cursos.models.entity.CursoAlumno;
import com.iessanvicente.microservicios.app.cursos.models.services.ICursoService;
import com.iessanvicente.microservicios.commons.alumnos.models.entities.Alumno;
import com.iessanvicente.microservicios.commons.controllers.CommonController;
import com.iessanvicente.microservicios.commons.examenes.models.entities.Examen;

@RestController
public class CursoController extends CommonController<Curso, ICursoService>{
	
	@Value("${config.balanceador.test}")
	private String balanceadorTest;
	
	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		Map<String, Object> response = new HashMap<>();
		response.put("cursos", service.findAll());
		response.put("balanceador", balanceadorTest);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Curso> cursos = ((List<Curso>) service.findAll()).stream()
			.map(c -> {
				c.getCursosAlumnos().forEach(ca->{
					Alumno a = new Alumno();
					a.setId(ca.getAlumnoId());
					c.addAlumno(a);
				});
				return c;
			})
			.collect(Collectors.toList());
		return ResponseEntity.ok(cursos);
	}
	
	@GetMapping("/page")
	public ResponseEntity<?> listar(Pageable pageable){
		List<Curso> cursos = service.findAll(pageable).stream()
				.map(c -> {
					c.getCursosAlumnos().forEach(ca->{
						Alumno a = new Alumno();
						a.setId(ca.getAlumnoId());
						c.addAlumno(a);
					});
					return c;
				})
				.collect(Collectors.toList());
			return ResponseEntity.ok(cursos);
	}
	
	
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> detalle(@PathVariable Long id){
		Curso curso = service.findById(id).orElse(null);
		if(curso == null) {
			return ResponseEntity.notFound().build();
		}
		curso.setAlumnos(
				service.obtenerAlumnosPorCurso(
						curso.getCursosAlumnos().stream()
						.map(ca -> ca.getAlumnoId())
						.collect(Collectors.toList())));
		return ResponseEntity.ok(curso);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificar(@PathVariable Long id, @Valid @RequestBody Curso curso, BindingResult result) {
		if(result.hasErrors()) {
			return getResponseErrores(result);
		}
		Curso cursoDB = service.findById(id).orElse(null);
		if(cursoDB == null) {
			return ResponseEntity.notFound().build();
		}
		cursoDB.setNombre(curso.getNombre() == null? 
				cursoDB.getNombre() : curso.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@PathVariable Long id, @RequestBody List<Alumno> alumnos) {

		Optional<Curso> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso cursoDB = o.get();
		alumnos.forEach(a -> {
			CursoAlumno cursoAlumno = new CursoAlumno();
			cursoAlumno.setAlumnoId(a.getId());
			cursoAlumno.setCurso(cursoDB);
			cursoDB.addCursoAlumno(cursoAlumno);
		});
		try {
			Curso c = service.save(cursoDB);
			return ResponseEntity.status(HttpStatus.CREATED).body(c);
		} catch(DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {	
		Optional<Curso> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso cursoDB = o.get();
		
		if(alumno.getId() == null) {
			return ResponseEntity.notFound().build();
		}
		CursoAlumno cursoAlumno = new CursoAlumno();
		cursoAlumno.setAlumnoId(alumno.getId());
		cursoDB.removeCursoAlumno(cursoAlumno);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?> asignarExamenes(@PathVariable Long id,@RequestBody List<Examen> examenes) {

		Optional<Curso> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso cursoDB = o.get();
		examenes.forEach(cursoDB::addExamen);
		try {
			Curso c = service.save(cursoDB);
			return ResponseEntity.status(HttpStatus.CREATED).body(c);
		} catch(DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamen(@PathVariable Long id, @RequestBody Examen examen) {
	
		Optional<Curso> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso cursoDB = o.get();
		
		if(examen.getId() == null) {
			return ResponseEntity.notFound().build();
		}
		
		cursoDB.removeExamen(examen);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumno(@PathVariable Long id){
		Optional<Curso> o = service.findByAlumnoId(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso curso = o.get();
		List<Long> examenesIdsRespondidos = service.obtenerExamenesIdsConRespuestasPorAlumno(id);
		if(examenesIdsRespondidos != null && !examenesIdsRespondidos.isEmpty()) {
			Set<Examen> examenes = curso.getExamenes()
					.stream()
					.map(examen -> {
						examen.setRespondido(examenesIdsRespondidos.contains(examen.getId()));
						return examen;
					})
					.collect(Collectors.toSet());
			curso.setExamenes(examenes);
		}
		
		return ResponseEntity.ok(curso);
	}
	
	@DeleteMapping("/eliminar-alumno/{id}")
	public ResponseEntity<?> eliminarAlumnoDelCursoPorId(@PathVariable Long id) {
		service.deleteCursoAlumnoById(id);
		return ResponseEntity.noContent().build();
	}
}
