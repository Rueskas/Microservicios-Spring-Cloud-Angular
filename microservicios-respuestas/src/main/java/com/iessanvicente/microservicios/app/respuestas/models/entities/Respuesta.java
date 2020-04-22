package com.iessanvicente.microservicios.app.respuestas.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Entity
@Table(name="respuestas")
public class Respuesta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String texto;
	
	//@ManyToOne(fetch=FetchType.LAZY)
	@Transient
	private Alumno alumno;
	
	@Column(name="alumno_id")
	private Long alumnoId;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Pregunta pregunta;
}
