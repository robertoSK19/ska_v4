package com.ska.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EquipoSoftware")
public class EquipoSoftware {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "mequipo_id_equipo", nullable = false)
	private MEquipo id_equipo;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "software_id_software", nullable = false)
	private Software id_software;
	
	public EquipoSoftware() {
		
	}

	public MEquipo getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(MEquipo id_equipo) {
		this.id_equipo = id_equipo;
	}

	public Software getId_software() {
		return id_software;
	}

	public void setId_software(Software id_software) {
		this.id_software = id_software;
	}

	public EquipoSoftware(MEquipo id_equipo, Software id_software) {
		super();
		this.id_equipo = id_equipo;
		this.id_software = id_software;
	}
	
	
}
