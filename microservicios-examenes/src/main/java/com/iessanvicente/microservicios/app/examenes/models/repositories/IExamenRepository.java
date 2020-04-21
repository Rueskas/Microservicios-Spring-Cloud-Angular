package com.iessanvicente.microservicios.app.examenes.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iessanvicente.microservicios.commons.examenes.models.entities.Examen;

public interface IExamenRepository extends CrudRepository<Examen, Long> {
	@Query("select e from Examen e where e.nombre like %?1%")
	public List<Examen> findByNombre(String term);
}
