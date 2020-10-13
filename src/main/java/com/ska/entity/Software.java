package com.ska.entity;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "software")
public class Software {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_software;

	private String nombre_software;
	private String no_serie;
	private Date vigencia_inicial;
	private Date vigencia_final;
	private String version;
	private String tipo_software;
	private String tipo_licencia;
	private String licencia_software;
	
	@Lob
	@Column(name = "factura")
	private byte[] factura;
	

	public Software() {
		
	}


	public Long getId_software() {
		return id_software;
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


	public String getNo_serie() {
		return no_serie;
	}


	public void setNo_serie(String no_serie) {
		this.no_serie = no_serie;
	}


	public Date getVigencia_inicial() {
		return vigencia_inicial;
	}


	public void setVigencia_inicial(Date vigencia_inicial) {
		this.vigencia_inicial = vigencia_inicial;
	}


	public Date getVigencia_final() {
		return vigencia_final;
	}


	public void setVigencia_final(Date vigencia_final) {
		this.vigencia_final = vigencia_final;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getTipo_software() {
		return tipo_software;
	}


	public void setTipo_software(String tipo_software) {
		this.tipo_software = tipo_software;
	}


	public String getTipo_licencia() {
		return tipo_licencia;
	}


	public void setTipo_licencia(String tipo_licencia) {
		this.tipo_licencia = tipo_licencia;
	}


	public String getLicencia_software() {
		return licencia_software;
	}


	public void setLicencia_software(String licencia_software) {
		this.licencia_software = licencia_software;
	}


	public byte[] getFactura() {
		return factura;
	}


	public void setFactura(byte[] factura) {
		this.factura = factura;
	}


	public Software(Long id_software, String nombre_software, String no_serie, Date vigencia_inicial,
			Date vigencia_final, String version, String tipo_software, String tipo_licencia, String licencia_software,
			byte[] factura) {
		super();
		this.id_software = id_software;
		this.nombre_software = nombre_software;
		this.no_serie = no_serie;
		this.vigencia_inicial = vigencia_inicial;
		this.vigencia_final = vigencia_final;
		this.version = version;
		this.tipo_software = tipo_software;
		this.tipo_licencia = tipo_licencia;
		this.licencia_software = licencia_software;
		this.factura = factura;
	}
	
}
