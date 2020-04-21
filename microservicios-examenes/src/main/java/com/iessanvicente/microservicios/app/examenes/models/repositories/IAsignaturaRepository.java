package com.iessanvicente.microservicios.app.examenes.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iessanvicente.microservicios.commons.examenes.models.entities.Asignatura;

public interface IAsignaturaRepository extends CrudRepository<Asignatura, Long> {

}
