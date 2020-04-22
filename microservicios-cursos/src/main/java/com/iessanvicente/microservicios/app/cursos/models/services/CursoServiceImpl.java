package com.iessanvicente.microservicios.app.cursos.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iessanvicente.microservicios.app.cursos.clients.RespuestaFeignClient;
import com.iessanvicente.microservicios.app.cursos.models.entity.Curso;
import com.iessanvicente.microservicios.app.cursos.models.repositories.ICursoRepository;
import com.iessanvicente.microservicios.commons.service.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, ICursoRepository> implements ICursoService {

	@Autowired
	private RespuestaFeignClient feignCliente;
	@Override
	@Transactional(readOnly=true)
	public Optional<Curso> findByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

	@Override
	public List<Long> obtenerExamenesIdsConRespuestasPorAlumno(Long alumnoId) {
		return feignCliente.obtenerExamenesIdsConRespuestasPorAlumno(alumnoId);
	}
	
}
