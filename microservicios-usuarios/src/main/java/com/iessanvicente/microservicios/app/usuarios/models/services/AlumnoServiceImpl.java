package com.iessanvicente.microservicios.app.usuarios.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iessanvicente.microservicios.app.usuarios.models.entity.Alumno;
import com.iessanvicente.microservicios.app.usuarios.models.repositories.IAlumnoRepository;

@Service
public class AlumnoServiceImpl implements IAlumnoService {
	@Autowired
	private IAlumnoRepository alumnoRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Alumno> findAll() {
		return alumnoRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Alumno> findById(Long id) {
		return alumnoRepository.findById(id);
	}

	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		return alumnoRepository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		alumnoRepository.deleteById(id);
	}

}
