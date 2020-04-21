package com.iessanvicente.microservicios.app.usuarios.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iessanvicente.microservicios.commons.alumnos.models.entities.Alumno;

public interface IAlumnoRepository extends JpaRepository<Alumno, Long> {

}
