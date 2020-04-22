package com.iessanvicente.microservicios.app.examenes.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iessanvicente.microservicios.commons.examenes.models.entities.Examen;

public interface IExamenRepository extends JpaRepository<Examen, Long> {
	@Query("select e from Examen e where e.nombre like %?1%")
	public List<Examen> findByNombre(String term);
	
	@Query("select DISTINCT(e.id) from Pregunta p join p.examen e where p.id in ?1")
	public List<Long> findExamenesIdConRespuestaByPreguntaIds(List<Long> preguntaIds);
}
