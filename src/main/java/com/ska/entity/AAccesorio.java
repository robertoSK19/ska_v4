package com.ska.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "aaccesorio")
public class AAccesorio {

	@Id//atributo Llave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)//se generar los valores de la llave primaria,
	private Long id_aaccesorio;
	//falta foreign key
	//Relacion de llave foranea con la clase Accesorio
	@ManyToOne //Relacion de N:1
	@JoinColumn(name = "id_accesorio", nullable = false)//Nombre del atributo o columna, con la cual debe hacer el join
	private Accesorio accesorio; //atributo accesorio
	//Relacion de llave foranea con la clase Asignacion
	@OneToOne
	@JoinColumn(name="id_asignacion",nullable = false)
	private Asignacion asignacion;



	public Long getId_aaccesorio() {
		return id_aaccesorio;
	}

	public void setId_aaccesorio(Long id_aaccesorio) {
		this.id_aaccesorio = id_aaccesorio;
	}

	public Accesorio getAccesorio() {
		return accesorio;
	}

	public void setAccesorio(Accesorio accesorio) {
		this.accesorio = accesorio;
	}

	public Asignacion getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(Asignacion asignacion) {
		this.asignacion = asignacion;
	}
	
	public AAccesorio () {
		
	}
	public AAccesorio(Long id_aaccesorio, Accesorio accesorio, Asignacion asignacion) {
		super();
		this.id_aaccesorio = id_aaccesorio;
		this.accesorio = accesorio;
		this.asignacion = asignacion;
	}
	
}
