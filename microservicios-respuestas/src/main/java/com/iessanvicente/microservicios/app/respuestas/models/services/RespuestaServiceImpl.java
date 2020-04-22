package com.iessanvicente.microservicios.app.respuestas.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessanvicente.microservicios.app.respuestas.models.entities.Respuesta;
import com.iessanvicente.microservicios.app.respuestas.models.repositories.IRespuestaRepository;

@Service
public class RespuestaServiceImpl implements IRespuestaService{
	@Autowired
	private IRespuestaRepository repository;
	@Override
	public List<Respuesta> saveAll(List<Respuesta> respuestas) {
		return (List<Respuesta>) repository.saveAll(respuestas);
	}
	@Override
	public List<Respuesta> findByAlumnoByExamen(Long alumnoId, Long examenId) {
		return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
	}
	@Override
	public List<Long> findExamenesIdConRespuestasByAlumnoId(Long alumnoId) {
		return repository.findExamenesIdConRespuestaByAlumno(alumnoId);
	}

}
