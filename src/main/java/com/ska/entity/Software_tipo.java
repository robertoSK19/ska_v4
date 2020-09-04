package com.ska.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "software_tipo")
public class Software_tipo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_software_tipo;
	private String nombre_software_tipo;

	public Long getId_software_tipo() {
		return id_software_tipo;
	}

	public void setId_software_tipo(Long id_software_tipo) {
		this.id_software_tipo = id_software_tipo;
	}

	public String getNombre_software_tipo() {
		return nombre_software_tipo;
	}

	public void setNombre_software_tipo(String nombre_software_tipo) {
		this.nombre_software_tipo = nombre_software_tipo;
	}

	public Software_tipo(Long id_software_tipo, String nombre_software_tipo) {
		super();
		this.id_software_tipo = id_software_tipo;
		this.nombre_software_tipo = nombre_software_tipo;
	}

	public Software_tipo() {
		
	}
}
