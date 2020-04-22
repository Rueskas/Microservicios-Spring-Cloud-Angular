package com.iessanvicente.microservicios.app.respuestas.models.services;

import java.util.List;

import com.iessanvicente.microservicios.app.respuestas.models.entities.Respuesta;

public interface IRespuestaService {
	public List<Respuesta> saveAll(List<Respuesta> respuestas);
	public List<Respuesta> findByAlumnoByExamen(Long alumnoId, Long examenId);
	public List<Long> findExamenesIdConRespuestasByAlumnoId(Long alumnoId);
	public List<Respuesta> findByAlumnoId(Long alumnoId);
}
