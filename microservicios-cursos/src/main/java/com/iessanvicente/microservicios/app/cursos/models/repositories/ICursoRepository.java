package com.iessanvicente.microservicios.app.cursos.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iessanvicente.microservicios.app.cursos.models.entity.Curso;

public interface ICursoRepository extends CrudRepository<Curso, Long>{
	@Query("select c from Curso c join fetch c.alumnos a where a.id = ?1")
	public Optional<Curso> findCursoByAlumnoId(Long id);
}
