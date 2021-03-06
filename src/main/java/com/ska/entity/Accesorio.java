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
	private Long id_equipo;
	// cambio 24/08/2020
	private float costo; 
	private String descripcion;
	private String capacidad;
	private String tipo_disco_duro;
	private String ram_bus;
	private String ram_ranura;

	@ManyToOne//Relacion de N:1, con la tabla EstatusRecurso
	@JoinColumn(name = "id_estatus", nullable = false)//nombre del atributo en la tabla EstatusRecurso
	private EstatusRecurso id_estatus; //atributo EstatusRecurso

	//constructor Vacio
	public Accesorio() {
	}

	//Constructor
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

	public Long getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(Long id_equipo) {
		this.id_equipo = id_equipo;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public String getTipo_disco_duro() {
		return tipo_disco_duro;
	}

	public void setTipo_disco_duro(String tipo_disco_duro) {
		this.tipo_disco_duro = tipo_disco_duro;
	}

	public String getRam_bus() {
		return ram_bus;
	}

	public void setRam_bus(String ram_bus) {
		this.ram_bus = ram_bus;
	}

	public String getRam_ranura() {
		return ram_ranura;
	}

	public void setRam_ranura(String ram_ranura) {
		this.ram_ranura = ram_ranura;
	}

	public EstatusRecurso getId_estatus() {
		return id_estatus;
	}

	public void setId_estatus(EstatusRecurso id_estatus) {
		this.id_estatus = id_estatus;
	}

	public Accesorio(Long id_accesorio, String nombre_accesorio, String marca, String modelo, String producto,
			String hecho_en, String serie, Long id_equipo, float costo, String descripcion, String capacidad,
			String tipo_disco_duro, String ram_bus, String ram_ranura, EstatusRecurso id_estatus) {
		super();
		this.id_accesorio = id_accesorio;
		this.nombre_accesorio = nombre_accesorio;
		this.marca = marca;
		this.modelo = modelo;
		this.producto = producto;
		this.hecho_en = hecho_en;
		this.serie = serie;
		this.id_equipo = id_equipo;
		this.costo = costo;
		this.descripcion = descripcion;
		this.capacidad = capacidad;
		this.tipo_disco_duro = tipo_disco_duro;
		this.ram_bus = ram_bus;
		this.ram_ranura = ram_ranura;
		this.id_estatus = id_estatus;
	}

}
