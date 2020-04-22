package com.iessanvicente.microservicios.app.respuestas.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iessanvicente.microservicios.app.respuestas.models.entities.Respuesta;

public interface IRespuestaRepository extends CrudRepository<Respuesta, Long>{
	@Query("select r "
			+ "from Respuesta r "
			+ "join fetch r.pregunta p "
			+ "join fetch p.examen e "
			+ "where r.alumnoId = ?1 and e.id = ?2")
	public List<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	
	@Query("select DISTINCT(e.id) from Respuesta r join r.pregunta p join p.examen e where r.alumnoId=?1")
	public List<Long> findExamenesIdConRespuestaByAlumno(Long alumnoId);
}
