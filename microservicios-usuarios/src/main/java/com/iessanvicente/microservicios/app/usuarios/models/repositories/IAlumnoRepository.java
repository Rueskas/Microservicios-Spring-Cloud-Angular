package com.iessanvicente.microservicios.app.usuarios.models.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iessanvicente.microservicios.commons.alumnos.models.entities.Alumno;

public interface IAlumnoRepository extends JpaRepository<Alumno, Long> {
	@Query("select a from Alumno a where upper(a.nombre) like concat('%',upper(?1),'%') or upper(a.apellidos) like concat('%',upper(?1),'%')")
	public List<Alumno> findByNombreOrApellido(String term);
	
	public List<Alumno> findAllByOrderByIdAsc();
	public Page<Alumno> findAllByOrderByIdAsc(Pageable pageable);
}
