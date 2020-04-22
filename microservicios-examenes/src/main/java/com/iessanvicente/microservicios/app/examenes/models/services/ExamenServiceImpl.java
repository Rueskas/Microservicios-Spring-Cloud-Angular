package com.iessanvicente.microservicios.app.examenes.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iessanvicente.microservicios.app.examenes.models.repositories.IAsignaturaRepository;
import com.iessanvicente.microservicios.app.examenes.models.repositories.IExamenRepository;
import com.iessanvicente.microservicios.commons.examenes.models.entities.Asignatura;
import com.iessanvicente.microservicios.commons.examenes.models.entities.Examen;
import com.iessanvicente.microservicios.commons.service.CommonServiceImpl;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, IExamenRepository> implements IExamenService {
	@Autowired
	private IAsignaturaRepository asignaturaRepository;
	@Override
	@Transactional(readOnly=true)
	public List<Examen> findByNombre(String term) {
		return repository.findByNombre(term);
	}
	@Override
	@Transactional(readOnly=true)
	public List<Asignatura> findAllAsignaturas() {
		return (List<Asignatura>) asignaturaRepository.findAll();
	}
	@Override
	@Transactional(readOnly=true)
	public List<Long> findByPreguntaIds(List<Long> preguntaIds) {
		return repository.findExamenesIdConRespuestaByPreguntaIds(preguntaIds);
	}

}
