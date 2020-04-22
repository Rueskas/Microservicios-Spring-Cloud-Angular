package com.iessanvicente.microservicios.app.cursos.clients;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iessanvicente.microservicios.commons.alumnos.models.entities.Alumno;

@FeignClient(name="microservicio-usuarios")
public interface AlumnoFeignClient {
	@GetMapping("/por-curso")
	public Set<Alumno> obtenerAlumnosPorCurso(@RequestParam List<Long> ids);
}
