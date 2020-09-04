package com.ska.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "software")
public class Software {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_software;

	private String nombre_software;
	private String no_serie;
	private Date fecha_licencia;

	@ManyToOne
	@JoinColumn(name = "id_dequipo", nullable = false)
	private DEquipo id_dequipo;

	@ManyToOne
	@JoinColumn(name = "id_software_tipo", nullable = false)
	private Software_tipo id_software_tipo;

	public Software_tipo getId_software_tipo() {
		return id_software_tipo;
	}

	public void setId_software_tipo(Software_tipo id_software_tipo) {
		this.id_software_tipo = id_software_tipo;
	}

	public Long getId_software() {
		return id_software;
	}

	public String getNo_serie() {
		return no_serie;
	}

	public void setNo_serie(String no_serie) {
		this.no_serie = no_serie;
	}

	public Date getFecha_licencia() {
		return fecha_licencia;
	}

	public void setFecha_licencia(Date fecha_licencia) {
		this.fecha_licencia = fecha_licencia;
	}

	public DEquipo getId_dequipo() {
		return id_dequipo;
	}

	public void setId_dequipo(DEquipo id_dequipo) {
		this.id_dequipo = id_dequipo;
	}

	public void setId_software(Long id_software) {
		this.id_software = id_software;
	}

	public String getNombre_software() {
		return nombre_software;
	}

	public void setNombre_software(String nombre_software) {
		this.nombre_software = nombre_software;
	}

	public Software(Long id_software, String nombre_software, String no_serie, Date fecha_licencia, DEquipo id_dequipo,
			Software_tipo id_software_tipo) {
		super();
		this.id_software = id_software;
		this.nombre_software = nombre_software;
		this.no_serie = no_serie;
		this.fecha_licencia = fecha_licencia;
		this.id_dequipo = id_dequipo;
		this.id_software_tipo = id_software_tipo;
	}

	public Software() {
	}

}
