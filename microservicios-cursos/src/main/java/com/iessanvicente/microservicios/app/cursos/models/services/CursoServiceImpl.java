package com.iessanvicente.microservicios.app.cursos.models.services;

import org.springframework.stereotype.Service;

import com.iessanvicente.microservicios.app.cursos.models.entity.Curso;
import com.iessanvicente.microservicios.app.cursos.models.repositories.ICursoRepository;
import com.iessanvicente.microservicios.commons.service.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, ICursoRepository> implements ICursoService {

}
