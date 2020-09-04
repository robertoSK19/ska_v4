package com.ska.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accesorio")
public class Accesorio {
	
	@Id//etiqueta para llave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)//se generar los valores de la llave primaria, 
	//"IDENTITY" que quiere decir que mapea la clave a una columna contadora;
	private Long id_accesorio;//atributo de llave primaria
	//Atributos de la clase/tabla
	private String nombre_accesorio;
	private String marca;
	private String modelo;
	private String producto;
	private String hecho_en;
	private String serie;

	@ManyToOne//Relacion de N:1, con la tabla EstatusRecurso
	@JoinColumn(name = "id_estatus", nullable = false)//nombre del atributo en la tabla EstatusRecurso
	private EstatusRecurso id_estatus; //atributo EstatusRecurso

	//constructor Vacio
	public Accesorio() {
	}

	//constructor 
	public Accesorio(Long id_accesorio, String nombre_accesorio, String marca, String modelo, String producto,
			String hecho_en, String serie, EstatusRecurso estatus) {
		super();
		this.id_accesorio = id_accesorio;
		this.nombre_accesorio = nombre_accesorio;
		this.marca = marca;
		this.modelo = modelo;
		this.producto = producto;
		this.hecho_en = hecho_en;
		this.serie = serie;
		this.id_estatus = estatus;
	}

	//metodos get´s y set´s de los atributos
	public EstatusRecurso getid_Estatus() {
		return id_estatus;
	}

	public void setid_Estatus(EstatusRecurso estatus) {
		this.id_estatus = estatus;
	}

	public Long getId_accesorio() {
		return id_accesorio;
	}

	public void setId_accesorio(Long id_accesorio) {
		this.id_accesorio = id_accesorio;
	}

	public String getNombre_accesorio() {
		return nombre_accesorio;
	}

	public void setNombre_accesorio(String nombre_accesorio) {
		this.nombre_accesorio = nombre_accesorio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getHecho_en() {
		return hecho_en;
	}

	public void setHecho_en(String hecho_en) {
		this.hecho_en = hecho_en;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	@Override //Metodo ToString
	public String toString() {
		return "Accesorio [id_accesorio=" + id_accesorio + ", nombre_accesorio=" + nombre_accesorio + ", marca=" + marca
				+ ", modelo=" + modelo + ", producto=" + producto + ", hecho_en=" + hecho_en + ", serie=" + serie
				+ ", estatus=" + id_estatus + "]";
	}

}
