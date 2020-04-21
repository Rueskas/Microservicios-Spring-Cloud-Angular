package com.iessanvicente.microservicios.app.examenes.models.services;

import java.util.List;

import com.iessanvicente.microservicios.commons.examenes.models.entities.Asignatura;
import com.iessanvicente.microservicios.commons.examenes.models.entities.Examen;
import com.iessanvicente.microservicios.commons.service.ICommonService;

public interface IExamenService extends ICommonService<Examen> {
	public List<Examen> findByNombre(String term);
	public List<Asignatura> findAllAsignaturas();
}
