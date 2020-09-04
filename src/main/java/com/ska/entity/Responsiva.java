package com.ska.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="responsiva")
public class Responsiva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_responsiva;
	private String estatus_responsiva;
	private Date fecha_responsiva;
	
	@OneToOne
	@JoinColumn(name="id_asignacion",nullable = false)
	private Asignacion id_asignacion;

	public Long getId_responsiva() {
		return id_responsiva;
	}

	public void setId_responsiva(Long id_responsiva) {
		this.id_responsiva = id_responsiva;
	}

	public String getEstatus_responsiva() {
		return estatus_responsiva;
	}

	public void setEstatus_responsiva(String estatus_responsiva) {
		this.estatus_responsiva = estatus_responsiva;
	}

	public Date getFecha_responsiva() {
		return fecha_responsiva;
	}

	public void setFecha_responsiva(Date fecha_responsiva) {
		this.fecha_responsiva = fecha_responsiva;
	}

	public Asignacion getId_asignacion() {
		return id_asignacion;
	}

	public void setId_asignacion(Asignacion id_asignacion) {
		this.id_asignacion = id_asignacion;
	}

	public Responsiva(Long id_responsiva, String estatus_responsiva, Date fecha_responsiva, Asignacion id_asignacion) {
		super();
		this.id_responsiva = id_responsiva;
		this.estatus_responsiva = estatus_responsiva;
		this.fecha_responsiva = fecha_responsiva;
		this.id_asignacion = id_asignacion;
	}

	public Responsiva() {
		
	}

		
	
}
