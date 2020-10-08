package com.ska.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Historico")
public class Historico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_historico;
	private String nombre_equipo;
	private String marca;
	private String modelo;
	private String numero_serie;
	private String modelo_equipo_cmd;
	private String numero_serie_cmd;
	private String procesador;
	private int ram;
	private String disco_duro;
	private String tipo_computadora;
	private String fecha_fabricacion;
	private String nombre_sistema_operativo;
	private String tipo_sistema_operativo;
	private String direccion_mac;
	private String generacion_procesador;
	private String tipo_disco_duro;
	private String tamano_pantalla;
	
	@JoinTable(name = "equiposoftware",
			joinColumns = @JoinColumn (name = "id_software_historico", nullable = false),
			inverseJoinColumns = @JoinColumn (name = "software_id_software", nullable = false))
	@ManyToMany
	private List<Software> software;
		
	public Historico() {
		
	}

	public long getId_historico() {
		return id_historico;
	}

	public void setId_historico(long id_historico) {
		this.id_historico = id_historico;
	}

	public String getNombre_equipo() {
		return nombre_equipo;
	}

	public void setNombre_equipo(String nombre_equipo) {
		this.nombre_equipo = nombre_equipo;
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

	public String getNumero_serie() {
		return numero_serie;
	}

	public void setNumero_serie(String numero_serie) {
		this.numero_serie = numero_serie;
	}

	public String getModelo_equipo_cmd() {
		return modelo_equipo_cmd;
	}

	public void setModelo_equipo_cmd(String modelo_equipo_cmd) {
		this.modelo_equipo_cmd = modelo_equipo_cmd;
	}

	public String getNumero_serie_cmd() {
		return numero_serie_cmd;
	}

	public void setNumero_serie_cmd(String numero_serie_cmd) {
		this.numero_serie_cmd = numero_serie_cmd;
	}

	public String getProcesador() {
		return procesador;
	}

	public void setProcesador(String procesador) {
		this.procesador = procesador;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public String getDisco_duro() {
		return disco_duro;
	}

	public void setDisco_duro(String disco_duro) {
		this.disco_duro = disco_duro;
	}

	public String getTipo_computadora() {
		return tipo_computadora;
	}

	public void setTipo_computadora(String tipo_computadora) {
		this.tipo_computadora = tipo_computadora;
	}

	public String getFecha_fabricacion() {
		return fecha_fabricacion;
	}

	public void setFecha_fabricacion(String fecha_fabricacion) {
		this.fecha_fabricacion = fecha_fabricacion;
	}

	public String getNombre_sistema_operativo() {
		return nombre_sistema_operativo;
	}

	public void setNombre_sistema_operativo(String nombre_sistema_operativo) {
		this.nombre_sistema_operativo = nombre_sistema_operativo;
	}

	public String getTipo_sistema_operativo() {
		return tipo_sistema_operativo;
	}

	public void setTipo_sistema_operativo(String tipo_sistema_operativo) {
		this.tipo_sistema_operativo = tipo_sistema_operativo;
	}

	public String getDireccion_mac() {
		return direccion_mac;
	}

	public void setDireccion_mac(String direccion_mac) {
		this.direccion_mac = direccion_mac;
	}

	public String getGeneracion_procesador() {
		return generacion_procesador;
	}

	public void setGeneracion_procesador(String generacion_procesador) {
		this.generacion_procesador = generacion_procesador;
	}

	public String getTipo_disco_duro() {
		return tipo_disco_duro;
	}

	public void setTipo_disco_duro(String tipo_disco_duro) {
		this.tipo_disco_duro = tipo_disco_duro;
	}

	public String getTamano_pantalla() {
		return tamano_pantalla;
	}

	public void setTamano_pantalla(String tamano_pantalla) {
		this.tamano_pantalla = tamano_pantalla;
	}

	public List<Software> getSoftware() {
		return software;
	}

	public void setSoftware(List<Software> software) {
		this.software = software;
	}

	public Historico(long id_historico, String nombre_equipo, String marca, String modelo, String numero_serie,
			String modelo_equipo_cmd, String numero_serie_cmd, String procesador, int ram, String disco_duro,
			String tipo_computadora, String fecha_fabricacion, String nombre_sistema_operativo,
			String tipo_sistema_operativo, String direccion_mac, String generacion_procesador, String tipo_disco_duro,
			String tamaño_pantalla, List<Software> software) {
		super();
		this.id_historico = id_historico;
		this.nombre_equipo = nombre_equipo;
		this.marca = marca;
		this.modelo = modelo;
		this.numero_serie = numero_serie;
		this.modelo_equipo_cmd = modelo_equipo_cmd;
		this.numero_serie_cmd = numero_serie_cmd;
		this.procesador = procesador;
		this.ram = ram;
		this.disco_duro = disco_duro;
		this.tipo_computadora = tipo_computadora;
		this.fecha_fabricacion = fecha_fabricacion;
		this.nombre_sistema_operativo = nombre_sistema_operativo;
		this.tipo_sistema_operativo = tipo_sistema_operativo;
		this.direccion_mac = direccion_mac;
		this.generacion_procesador = generacion_procesador;
		this.tipo_disco_duro = tipo_disco_duro;
		this.tamano_pantalla = tamano_pantalla;
		this.software = software;
	}
}
