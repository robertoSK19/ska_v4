package com.ska.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="musuario")
public class MUsuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;
	private String nombres;
	private String apellido_p;
	private String apellido_m;
	private String correo;
	private String contraseña;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_creacion;
	
	
	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false)
	private Roles rol;
	
	public MUsuario() {
		
	}	

	public MUsuario(Long id_usuario, String nombres, String apellido_p, String apellido_m, String correo,
			String contraseña, Date fecha_creacion, Roles rol) {
		super();
		this.id_usuario = id_usuario;
		this.nombres = nombres;
		this.apellido_p = apellido_p;
		this.apellido_m = apellido_m;
		this.correo = correo;
		this.contraseña = contraseña;
		this.fecha_creacion = fecha_creacion;
		this.rol = rol;
	}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellido_p() {
		return apellido_p;
	}

	public void setApellido_p(String apellido_p) {
		this.apellido_p = apellido_p;
	}

	public String getApellido_m() {
		return apellido_m;
	}

	public void setApellido_m(String apellido_m) {
		this.apellido_m = apellido_m;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_usuario == null) ? 0 : id_usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MUsuario other = (MUsuario) obj;
		if (id_usuario == null) {
			if (other.id_usuario != null)
				return false;
		} else if (!id_usuario.equals(other.id_usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MUsuario [id_usuario=" + id_usuario + ", nombres=" + nombres + ", apellido_p=" + apellido_p
				+ ", apellido_m=" + apellido_m + ", correo=" + correo + ", contraseña=" + contraseña
				+ ", fecha_creacion=" + fecha_creacion + ", rol=" + rol + "]";
	}
	
	

}
