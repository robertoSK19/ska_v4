package com.ska.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estatusrecurso")
public class EstatusRecurso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_estatus;
	private String nombre_estatus;

	public Long getId_estatus() {
		return id_estatus;
	}

	public void setId_estatus(Long id_estatus) {
		this.id_estatus = id_estatus;
	}

	public String getNombre_estatus() {
		return nombre_estatus;
	}

	public void setNombre_estatus(String nombre_estatus) {
		this.nombre_estatus = nombre_estatus;
	}
	
	//R
	
	public EstatusRecurso() {}
	public EstatusRecurso(Long id_estatus, String nombre_estatus) {
		super();
		this.id_estatus = id_estatus;
		this.nombre_estatus = nombre_estatus;
	}
	
	
}
