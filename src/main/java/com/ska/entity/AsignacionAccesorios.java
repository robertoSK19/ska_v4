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
@Table(name = "asignacionAccesorios")
public class AsignacionAccesorios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_asignacion;
	private String nombre_consultor;
	private Date fecha_asignacion;
	private float costo;
	private String letra;
	private String Usuario;
	
	@OneToOne
	@JoinColumn(name="id_estatus",nullable = false)
	private EstatusRecurso estatusrecurso;
	
	public AsignacionAccesorios() {
		
	}

	public Long getId_asignacion() {
		return id_asignacion;
	}

	public void setId_asignacion(Long id_asignacion) {
		this.id_asignacion = id_asignacion;
	}

	public String getNombre_consultor() {
		return nombre_consultor;
	}

	public void setNombre_consultor(String nombre_consultor) {
		this.nombre_consultor = nombre_consultor;
	}

	public Date getFecha_asignacion() {
		return fecha_asignacion;
	}

	public void setFecha_asignacion(Date fecha_asignacion) {
		this.fecha_asignacion = fecha_asignacion;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public EstatusRecurso getEstatusrecurso() {
		return estatusrecurso;
	}

	public void setEstatusrecurso(EstatusRecurso estatusrecurso) {
		this.estatusrecurso = estatusrecurso;
	}

	public AsignacionAccesorios(Long id_asignacion, String nombre_consultor, Date fecha_asignacion, float costo,
			String letra, String usuario, EstatusRecurso estatusrecurso) {
		super();
		this.id_asignacion = id_asignacion;
		this.nombre_consultor = nombre_consultor;
		this.fecha_asignacion = fecha_asignacion;
		this.costo = costo;
		this.letra = letra;
		Usuario = usuario;
		this.estatusrecurso = estatusrecurso;
	}
	
	
}
