package com.iessanvicente.microservicios.app.respuestas.models.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.iessanvicente.microservicios.commons.alumnos.models.entities.Alumno;
import com.iessanvicente.microservicios.commons.examenes.models.entities.Pregunta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="respuestas")
public class Respuesta {
	
	@Id
	private String id;
	
	private String texto;
	
	@Transient
	private Alumno alumno;
	
	private Long alumnoId;
	
	@Transient
	private Pregunta pregunta;
	
	private Long preguntaId;
}
