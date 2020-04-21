package com.iessanvicente.microservicios.commons.examenes.models.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="asignaturas")
public class Asignatura {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	@JsonIgnoreProperties(value={"padre","hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="padre")
	private Set<Asignatura> hijos = new HashSet<>();

	@JsonIgnoreProperties(value={"hijos","hibernateLazyInitializer", "handler"}, allowSetters=true)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="padre_id")
	private Asignatura padre;
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Asignatura)) {
			return false;
		}
		
		return this.id == ((Asignatura) obj).getId();
	}
}
