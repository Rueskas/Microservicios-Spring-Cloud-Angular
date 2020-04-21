package com.iessanvicente.microservicios.app.cursos.models.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.iessanvicente.microservicios.commons.alumnos.models.entities.Alumno;

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
@Table(name="cursos")
public class Curso {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_At")
	private Date createAt;
	
	@OneToMany(fetch = FetchType.LAZY)
	Set<Alumno> alumnos = new HashSet<>();
	
	@PrePersist
	public void prePersis() {
		createAt = new Date();
	}
	
	public void addAlumno(Alumno alumno) {
		this.alumnos.add(alumno);
	}
	
	public void removeAlumno(Alumno alumno) {
		this.alumnos.removeIf(a -> a.getId() == alumno.getId());
	}
}