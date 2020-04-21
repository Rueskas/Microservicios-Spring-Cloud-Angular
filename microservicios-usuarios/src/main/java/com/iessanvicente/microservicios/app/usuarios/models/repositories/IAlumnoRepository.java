package com.iessanvicente.microservicios.app.usuarios.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iessanvicente.microservicios.commons.alumnos.models.entities.Alumno;

public interface IAlumnoRepository extends JpaRepository<Alumno, Long> {
	@Query("select a from Alumno a where a.nombre like %?1% or a.apellidos like %?1%")
	public List<Alumno> findByNombreOrApellido(String term);
}
