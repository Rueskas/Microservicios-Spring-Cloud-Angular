package com.iessanvicente.microservicios.app.usuarios.models.services;

import org.springframework.stereotype.Service;

import com.iessanvicente.microservicios.app.usuarios.models.entity.Alumno;
import com.iessanvicente.microservicios.app.usuarios.models.repositories.IAlumnoRepository;
import com.iessanvicente.microservicios.commons.service.CommonServiceImpl;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, IAlumnoRepository> implements IAlumnoService {
}
