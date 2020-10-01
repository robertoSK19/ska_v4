package com.ska.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import clasesid.AccesorioNPK;

@Entity
@Table(name = "accesorion")
@IdClass(value = AccesorioNPK.class)
public class AccesoriosN {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_asignacion", nullable = false)
	private AsignacionAccesorios id_asignacion;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_accesorio", nullable = false)
	private Accesorio id_accesorio;
	
	public AccesoriosN () {
		
	}

	public AsignacionAccesorios getId_asignacion() {
		return id_asignacion;
	}

	public void setId_asignacion(AsignacionAccesorios id_asignacion) {
		this.id_asignacion = id_asignacion;
	}

	public Accesorio getId_accesorio() {
		return id_accesorio;
	}

	public void setId_accesorio(Accesorio id_accesorio) {
		this.id_accesorio = id_accesorio;
	}

	public AccesoriosN(AsignacionAccesorios id_asignacion, Accesorio id_accesorio) {
		super();
		this.id_asignacion = id_asignacion;
		this.id_accesorio = id_accesorio;
	}
	
	
}
