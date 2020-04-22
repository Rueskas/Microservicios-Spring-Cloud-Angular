package com.iessanvicente.microservicios.app.cursos.models.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.iessanvicente.microservicios.app.cursos.models.entity.Curso;
import com.iessanvicente.microservicios.commons.alumnos.models.entities.Alumno;
import com.iessanvicente.microservicios.commons.service.ICommonService;

public interface ICursoService extends ICommonService<Curso> {
	public Optional<Curso> findByAlumnoId(Long id);
	public List<Long> obtenerExamenesIdsConRespuestasPorAlumno(Long alumnoId);
	public Set<Alumno> obtenerAlumnosPorCurso(List<Long> ids);
	public void deleteCursoAlumnoById(Long id);
}
