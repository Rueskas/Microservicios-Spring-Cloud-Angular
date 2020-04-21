package com.iessanvicente.microservicios.app.cursos.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iessanvicente.microservicios.app.cursos.models.entity.Curso;

public interface ICursoRepository extends CrudRepository<Curso, Long>{

}
