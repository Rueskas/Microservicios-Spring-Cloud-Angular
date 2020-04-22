package com.iessanvicente.microservicios.app.cursos.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservicio-respuestas")
public interface RespuestaFeignClient {
	@GetMapping("/alumno/{alumnoId}/examenes-respondidos")
	public List<Long> obtenerExamenesIdsConRespuestasPorAlumno(@PathVariable Long alumnoId);
}
