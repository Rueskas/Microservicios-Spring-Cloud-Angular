package com.iessanvicente.microservicios.app.cursos.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.iessanvicente.microservicios.app.cursos.models.entity.Curso;

public interface ICursoRepository extends JpaRepository<Curso, Long>{
	@Query("select c from Curso c join fetch c.cursosAlumnos a where a.alumnoId = ?1")
	public Optional<Curso> findCursoByAlumnoId(Long id);
	
	@Modifying
	@Query("delete from CursoAlumno c where c.alumnoId = ?1")
	public void deleteCursoAlumnoById(Long alumnoId);
	
}
