package com.iessanvicente.microservicios.app.cursos.models.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iessanvicente.microservicios.app.cursos.clients.AlumnoFeignClient;
import com.iessanvicente.microservicios.app.cursos.clients.RespuestaFeignClient;
import com.iessanvicente.microservicios.app.cursos.models.entity.Curso;
import com.iessanvicente.microservicios.app.cursos.models.repositories.ICursoRepository;
import com.iessanvicente.microservicios.commons.alumnos.models.entities.Alumno;
import com.iessanvicente.microservicios.commons.service.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, ICursoRepository> implements ICursoService {

	@Autowired
	private RespuestaFeignClient respuestaFeignCliente;
	@Autowired
	private AlumnoFeignClient alumnoFeignClient;
	@Override
	@Transactional(readOnly=true)
	public Optional<Curso> findByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

	@Override
	public List<Long> obtenerExamenesIdsConRespuestasPorAlumno(Long alumnoId) {
		return respuestaFeignCliente.obtenerExamenesIdsConRespuestasPorAlumno(alumnoId);
	}

	@Override
	public Set<Alumno> obtenerAlumnosPorCurso(List<Long> ids) {
		return alumnoFeignClient.obtenerAlumnosPorCurso(ids);
	}

	@Override
	@Transactional
	public void deleteCursoAlumnoById(Long id) {
		repository.deleteCursoAlumnoById(id);
		
	}
	
}
