package com.iessanvicente.microservicios.app.usuarios.models.services;

import java.util.Optional;

import com.iessanvicente.microservicios.app.usuarios.models.entity.Alumno;

public interface IAlumnoService {
	public Iterable<Alumno> findAll();
	public Optional<Alumno> findById(Long id);
	public Alumno save(Alumno alumno);
	public void deleteById(Long id);
}
