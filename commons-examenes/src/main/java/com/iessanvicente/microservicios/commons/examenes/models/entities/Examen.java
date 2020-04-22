package com.iessanvicente.microservicios.commons.examenes.models.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
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
@Table(name="examenes")
public class Examen {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nombre;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt;

	@Setter(AccessLevel.NONE)
	@JsonIgnoreProperties(value={"examen"}, allowSetters=true)
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true, mappedBy="examen")
	private Set<Pregunta> preguntas = new HashSet<>();
	
	@NotNull
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="asignatura_id")
	private Asignatura asignatura;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	public void setPreguntas(Set<Pregunta> preguntas) {
		this.preguntas.clear();
        preguntas.forEach(this::addPregunta);
	}
	
	public void addPregunta(Pregunta pregunta) {
		pregunta.setExamen(this);
		this.preguntas.add(pregunta);
	}
	
	public void removePregunta(Pregunta pregunta) {
		pregunta.setExamen(null);
		this.preguntas.removeIf(p -> p.getId() == pregunta.getId());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Examen)) {
			return false;
		}
		
		return this.id == ((Examen) obj).getId();
	}
	
	
}
