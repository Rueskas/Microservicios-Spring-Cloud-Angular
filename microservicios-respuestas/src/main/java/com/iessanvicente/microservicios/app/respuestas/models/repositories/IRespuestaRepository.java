package com.iessanvicente.microservicios.app.respuestas.models.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.iessanvicente.microservicios.app.respuestas.models.entities.Respuesta;

public interface IRespuestaRepository extends MongoRepository<Respuesta, String>{
	/*@Query("select r "
			+ "from Respuesta r "
			+ "join fetch r.pregunta p "
			+ "join fetch p.examen e "
			+ "where r.alumnoId = ?1 and e.id = ?2")*/
	//public List<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	
	//@Query("select DISTINCT(e.id) from Respuesta r join r.pregunta p join p.examen e where r.alumnoId=?1")
	//public List<Long> findExamenesIdConRespuestaByAlumno(Long alumnoId);
	
	@Query("{'alumnoId':?0, 'preguntaId': {$in:?1}}")
	public List<Respuesta> findRespuestaByAlumnoByPreguntaIds(Long alumnoId, List<Long> preguntaIds);
	
	@Query("{'alumnoId': ?0}")
	public List<Respuesta> findByAlumnoId(Long alumnoId);
	
	@Query("{'alumnoId':?0, 'pregunta.examen.id':?1}")
	public List<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	
	@Query(value="{'alumnoId':?0}", fields= "{'pregunta.examen.id':1}")
	public List<Respuesta> findExamenesIdConRespuestaByAlumno(Long alumnoId);

	
}
