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
@Table(name = "dequipo")
public class DEquipo {
	@Id//etiqueta de llave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)//valor de tipo contador
	private Long id_dequipo;//atributo llave primaria
	//atributos de la clase
	private Date fecha_actualizacion_estatus;
	private int disco_duro_solido;
	private String comentarios;

	@OneToOne
	@JoinColumn(name = "id_equipo", nullable = false)
	private MEquipo mequipo;
	
	@OneToOne
	@JoinColumn(name = "id_estatus", nullable = false)
	private EstatusRecurso estatusRecurso;
	

	public Long getId_dequipo() {
		return id_dequipo;
	}

	public void setId_dequipo(Long id_dequipo) {
		this.id_dequipo = id_dequipo;
	}

	public Date getFecha_actualizacion_estatus() {
		return fecha_actualizacion_estatus;
	}

	public void setFecha_actualizacion_estatus(Date fecha_actualizacion_estatus) {
		this.fecha_actualizacion_estatus = fecha_actualizacion_estatus;
	}

	public int getDisco_duro_solido() {
		return disco_duro_solido;
	}

	public void setDisco_duro_solido(int disco_duro_solido) {
		this.disco_duro_solido = disco_duro_solido;
	}

	public MEquipo getMequipo() {
		return mequipo;
	}

	public void setMequipo(MEquipo mequipo) {
		this.mequipo = mequipo;
	}

	public EstatusRecurso getEstatusRecurso() {
		return estatusRecurso;
	}

	public void setEstatusRecurso(EstatusRecurso estatusRecurso) {
		this.estatusRecurso = estatusRecurso;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public DEquipo(Long id_dequipo, Date fecha_actualizacion_estatus, int disco_duro_solido, String comentarios,
			MEquipo mequipo, EstatusRecurso estatusRecurso) {
		super();
		this.id_dequipo = id_dequipo;
		this.fecha_actualizacion_estatus = fecha_actualizacion_estatus;
		this.disco_duro_solido = disco_duro_solido;
		this.comentarios = comentarios;
		this.mequipo = mequipo;
		this.estatusRecurso = estatusRecurso;
	}

	public DEquipo() {
		
	}

}
