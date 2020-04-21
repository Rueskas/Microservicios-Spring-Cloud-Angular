package com.iessanvicente.microservicios.app.cursos.models.services;

import java.util.Optional;

import com.iessanvicente.microservicios.app.cursos.models.entity.Curso;
import com.iessanvicente.microservicios.commons.service.ICommonService;

public interface ICursoService extends ICommonService<Curso> {
	public Optional<Curso> findByAlumnoId(Long id);
}
