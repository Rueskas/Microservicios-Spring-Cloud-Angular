package com.iessanvicente.microservicios.app.usuarios.models.services;

import java.util.List;

import com.iessanvicente.microservicios.commons.alumnos.models.entities.Alumno;
import com.iessanvicente.microservicios.commons.service.ICommonService;

public interface IAlumnoService extends ICommonService<Alumno> {
	public List<Alumno> findByNombreOrApellido(String term);
	public List<Alumno> findAllById(List<Long> ids);
}
