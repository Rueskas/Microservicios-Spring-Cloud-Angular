package com.iessanvicente.microservicios.app.usuarios.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iessanvicente.microservicios.app.usuarios.clients.CursoFeignClient;
import com.iessanvicente.microservicios.app.usuarios.models.repositories.IAlumnoRepository;
import com.iessanvicente.microservicios.commons.alumnos.models.entities.Alumno;
import com.iessanvicente.microservicios.commons.service.CommonServiceImpl;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, IAlumnoRepository> implements IAlumnoService {

	@Autowired
	private CursoFeignClient cursoFeignClient;
	
	@Override
	@Transactional(readOnly=true)
	public List<Alumno> findByNombreOrApellido(String term) {
		return repository.findByNombreOrApellido(term);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Alumno> findAllById(List<Long> ids) {
		return repository.findAllById(ids);
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		super.deleteById(id);
		cursoFeignClient.eliminarAlumnoDelCursoPorId(id);
	}
}
