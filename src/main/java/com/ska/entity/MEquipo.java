package com.ska.entity;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name="mequipo")
public class MEquipo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id_equipo;
	private String nombre_equipo;
	private String marca;
	private String modelo;
	private String numero_serie;
	private String modelo_equipo_cmd;
	private String numero_serie_cmd;
	private String procesador;
	private int ram;
	private String disco_duro;
	private String cuenta_usuario;
	private String cuenta_usuario_contraseña;
	private String tipo_computadora;
	private String fecha_fabricacion;
	private String nombre_sistema_operativo;
	private String tipo_sistema_operativo;
	private String direccion_mac;
	private String email_gnp;
	private String tipo_disco_duro;
	private String generacion_procesador;

	private String tamaño_pantalla;
	private String fecha_compra;
	private String lugar_compra;
	private String fecha_garantia_termino;

	private String id_historico_equipo;
	private String id_equipo_software;

	
	public String getId_historico_equipo() {
		return id_historico_equipo;
	}

	public void setId_historico_equipo(String id_historico_equipo) {
		this.id_historico_equipo = id_historico_equipo;
	}

	public String getId_equipo_software() {
		return id_equipo_software;
	}

	public void setId_equipo_software(String id_equipo_software) {
		this.id_equipo_software = id_equipo_software;
	}

	public String getTipo_disco_duro() {
		return tipo_disco_duro;
	}

	public void setTipo_disco_duro(String tipo_disco_duro) {
		this.tipo_disco_duro = tipo_disco_duro;
	}

	public String getGeneracion_procesador() {
		return generacion_procesador;
	}

	public void setGeneracion_procesador(String generacion_procesador) {
		this.generacion_procesador = generacion_procesador;
	}

	public String getTamaño_pantalla() {
		return tamaño_pantalla;
	}

	public void setTamaño_pantalla(String tamaño_pantalla) {
		this.tamaño_pantalla = tamaño_pantalla;
	}

	public String getFecha_compra() {
		return fecha_compra;
	}

	public void setFecha_compra(String fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	public String getLugar_compra() {
		return lugar_compra;
	}

	public void setLugar_compra(String lugar_compra) {
		this.lugar_compra = lugar_compra;
	}

	public String getFecha_garantia_termino() {
		return fecha_garantia_termino;
	}

	public void setFecha_garantia_termino(String fecha_garantia_termino) {
		this.fecha_garantia_termino = fecha_garantia_termino;
	}

	@JoinTable(name = "historicoequipo", 
			joinColumns = @JoinColumn (name= "id_historico_equipo", nullable = false),
			inverseJoinColumns = @JoinColumn(name="id_historico", nullable = false))
	@OneToMany
	private List<Historico> historico;
	
	@JoinTable(name = "equiposoftware", 
			joinColumns = @JoinColumn (name= "id_equipo_software", nullable = false),
			inverseJoinColumns = @JoinColumn(name="software_id_software", nullable = false))
	@ManyToMany
	private List<Software> software;
	
	public void addHistorico(Historico histo) {
		if(this.historico == null) {
			this.historico = new ArrayList<>();
		}
	}
	
	public void addSoftware(Software soft) {
		if(this.software == null) {
			this.software = new ArrayList<>();
		}
	}
	
	public MEquipo() {
		
	}

	public Long getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(Long id_equipo) {
		this.id_equipo = id_equipo;
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

	public String getCuenta_usuario() {
		return cuenta_usuario;
	}

	public void setCuenta_usuario(String cuenta_usuario) {
		this.cuenta_usuario = cuenta_usuario;
	}

	public String getCuenta_usuario_contraseña() {
		return cuenta_usuario_contraseña;
	}

	public void setCuenta_usuario_contraseña(String cuenta_usuario_contraseña) {
		this.cuenta_usuario_contraseña = cuenta_usuario_contraseña;
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

	public String getEmail_gnp() {
		return email_gnp;
	}

	public void setEmail_gnp(String email_gnp) {
		this.email_gnp = email_gnp;
	}


	public List<Historico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Historico> historico) {
		this.historico = historico;
	}

	public List<Software> getSoftware() {
		return software;
	}

	public void setSoftware(List<Software> software) {
		this.software = software;
	}

	public MEquipo(Long id_equipo, String nombre_equipo, String marca, String modelo, String numero_serie,
			String modelo_equipo_cmd, String numero_serie_cmd, String procesador, int ram, String disco_duro,
			String cuenta_usuario, String cuenta_usuario_contraseña, String tipo_computadora, String fecha_fabricacion,
			String nombre_sistema_operativo, String tipo_sistema_operativo, String direccion_mac, String email_gnp,
			String tipo_disco_duro, String generacion_procesador, String tamaño_pantalla, String fecha_compra,
			String lugar_compra, String fecha_garantia_termino, String id_historico_equipo, String id_equipo_software,
			List<Historico> historico, List<Software> software) {
		super();
		this.id_equipo = id_equipo;
		this.nombre_equipo = nombre_equipo;
		this.marca = marca;
		this.modelo = modelo;
		this.numero_serie = numero_serie;
		this.modelo_equipo_cmd = modelo_equipo_cmd;
		this.numero_serie_cmd = numero_serie_cmd;
		this.procesador = procesador;
		this.ram = ram;
		this.disco_duro = disco_duro;
		this.cuenta_usuario = cuenta_usuario;
		this.cuenta_usuario_contraseña = cuenta_usuario_contraseña;
		this.tipo_computadora = tipo_computadora;
		this.fecha_fabricacion = fecha_fabricacion;
		this.nombre_sistema_operativo = nombre_sistema_operativo;
		this.tipo_sistema_operativo = tipo_sistema_operativo;
		this.direccion_mac = direccion_mac;
		this.email_gnp = email_gnp;
		this.tipo_disco_duro = tipo_disco_duro;
		this.generacion_procesador = generacion_procesador;
		this.tamaño_pantalla = tamaño_pantalla;
		this.fecha_compra = fecha_compra;
		this.lugar_compra = lugar_compra;
		this.fecha_garantia_termino = fecha_garantia_termino;
		this.id_historico_equipo = id_historico_equipo;
		this.id_equipo_software = id_equipo_software;
		this.historico = historico;
		this.software = software;
	}

//	public MEquipo(Long id_equipo, String nombre_equipo, String marca, String modelo, String numero_serie,
//			String modelo_equipo_cmd, String numero_serie_cmd, String procesador, int ram, String disco_duro,
//			String cuenta_usuario, String cuenta_usuario_contraseña, String tipo_computadora, String fecha_fabricacion,
//			String nombre_sistema_operativo, String tipo_sistema_operativo, String direccion_mac, String email_gnp,
//			String tipo_disco_duro, String generacion_procesador, String tamaño_pantalla, String fecha_compra,
//			String lugar_compra, String fecha_garantia_termino, List<Historico> historico, List<Software> software) {
//		super();
//		this.id_equipo = id_equipo;
//		this.nombre_equipo = nombre_equipo;
//		this.marca = marca;
//		this.modelo = modelo;
//		this.numero_serie = numero_serie;
//		this.modelo_equipo_cmd = modelo_equipo_cmd;
//		this.numero_serie_cmd = numero_serie_cmd;
//		this.procesador = procesador;
//		this.ram = ram;
//		this.disco_duro = disco_duro;
//		this.cuenta_usuario = cuenta_usuario;
//		this.cuenta_usuario_contraseña = cuenta_usuario_contraseña;
//		this.tipo_computadora = tipo_computadora;
//		this.fecha_fabricacion = fecha_fabricacion;
//		this.nombre_sistema_operativo = nombre_sistema_operativo;
//		this.tipo_sistema_operativo = tipo_sistema_operativo;
//		this.direccion_mac = direccion_mac;
//		this.email_gnp = email_gnp;
//		this.tipo_disco_duro = tipo_disco_duro;
//		this.generacion_procesador = generacion_procesador;
//		this.tamaño_pantalla = tamaño_pantalla;
//		this.fecha_compra = fecha_compra;
//		this.lugar_compra = lugar_compra;
//		this.fecha_garantia_termino = fecha_garantia_termino;	
//		this.historico = historico;
//		this.software = software;
//	}
}
