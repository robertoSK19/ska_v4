package com.ska.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import clasesid.EquipoSoftwareSK;

@Entity
@Table(name = "equiposoftware")
@IdClass(value = EquipoSoftwareSK.class)
public class EquipoSoftware {
	
 
	@Id
	@ManyToOne
	@JoinColumn(name = "id_equipo_software", nullable = false)
	private MEquipo id_equipo;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_software_historico", nullable = false)
	private Historico id_historico;
	
	@Column(name="software_id_software", nullable = false)
	private Long id_software;

	

	public EquipoSoftware() {
		
	}

	@JoinTable(name = "software",
			joinColumns = @JoinColumn (name = "software_id_software", nullable = false),
			inverseJoinColumns = @JoinColumn (name = "id_software", nullable = false))
	@ManyToMany
	
	public Long getId_software() {
		return id_software;
	}

	public void setId_software(Long id_software) {
		this.id_software = id_software;
	}
	public MEquipo getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(MEquipo id_equipo) {
		this.id_equipo = id_equipo;
	}

	public Historico getId_historico() {
		return id_historico;
	}

	public void setId_historico(Historico id_historico) {
		this.id_historico = id_historico;
	}

	public EquipoSoftware(MEquipo id_equipo, Historico id_historico, Long id_software) {
		super();
		this.id_equipo = id_equipo;
		this.id_historico = id_historico;
		this.id_software = id_software;
	}
	
	
}
