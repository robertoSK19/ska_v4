package com.ska.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ska.entity.MEquipo;
import com.ska.repository.RepositoryMEquipo;

@RestController
@RequestMapping("equipos")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
public class MEquipoController {
	MEquipo aux;
	private static final Logger log = LoggerFactory.getLogger(MEquipoController.class);
	@Autowired
	private RepositoryMEquipo mequiporepositorio;
	
	@GetMapping(value="/get")
	public ResponseEntity<List<MEquipo>> VerMEquipoEstatus() {
		List<MEquipo> equipos = mequiporepositorio.findAll();
		return ResponseEntity.ok(equipos);
	}
	
	//@RequestMapping(value="{id}")
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<MEquipo> MEquipoId(@PathVariable("id") Long id){
		Optional<MEquipo> idequipo = mequiporepositorio.findById(id);
		if(idequipo.isPresent()) {
			return ResponseEntity.ok(idequipo.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value="")
	public ResponseEntity<MEquipo> Consulta_IdMEquipo(@RequestParam("id") Long id){
		Optional<MEquipo> idequipo = mequiporepositorio.findById(id);
		if(idequipo.isPresent()) {
			return ResponseEntity.ok(idequipo.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	@PostMapping(value="/post")
	public ResponseEntity<MEquipo> CrearEquipo(@RequestBody MEquipo equipo){
		this.aux = equipo;
		List<MEquipo> equiposR = mequiporepositorio.findAll();
		log.info("" + equiposR.size());
		String indiceEquipo = "";
		indiceEquipo = String.valueOf(equiposR.size()+1);
		log.info(indiceEquipo);
		this.aux.setId_equipo_software(indiceEquipo);
		this.aux.setId_historico_equipo(indiceEquipo);
		//equipo.setId_historico_equipo(200);
			MEquipo nuevoequipo = mequiporepositorio.save(equipo) ;
			return ResponseEntity.ok(nuevoequipo);
	}
	
	@PutMapping
	public ResponseEntity<MEquipo> EditarEquipo(@RequestBody MEquipo estatus){
		System.out.println(estatus.getId_equipo());
		Optional<MEquipo> optionalEquipo = mequiporepositorio.findById(estatus.getId_equipo());
		System.out.println(optionalEquipo);
		if(optionalEquipo.isPresent()) {
			MEquipo updateEquipo = optionalEquipo.get();
			
			updateEquipo.setNombre_equipo(estatus.getNombre_equipo());
			System.out.println(updateEquipo.getId_equipo());
			updateEquipo.setMarca(estatus.getMarca());
			updateEquipo.setModelo(estatus.getModelo());
			updateEquipo.setNumero_serie(estatus.getNumero_serie());
			updateEquipo.setModelo_equipo_cmd(estatus.getModelo_equipo_cmd());
			updateEquipo.setNumero_serie_cmd(estatus.getNumero_serie_cmd());
			updateEquipo.setProcesador(estatus.getProcesador());
			updateEquipo.setRam(estatus.getRam());
			updateEquipo.setDisco_duro(estatus.getDisco_duro());
			updateEquipo.setCuenta_usuario(estatus.getCuenta_usuario());
			updateEquipo.setCuenta_usuario_contraseña(estatus.getCuenta_usuario_contraseña());
			updateEquipo.setTipo_computadora(estatus.getTipo_computadora());
			updateEquipo.setNombre_sistema_operativo(estatus.getNombre_sistema_operativo());
			updateEquipo.setTipo_sistema_operativo(estatus.getTipo_sistema_operativo());
			updateEquipo.setDireccion_mac(estatus.getDireccion_mac());
			updateEquipo.setFecha_fabricacion(estatus.getFecha_fabricacion());
			updateEquipo.setTipo_disco_duro(estatus.getTipo_disco_duro());
			updateEquipo.setGeneracion_procesador(estatus.getGeneracion_procesador());
			updateEquipo.setTamaño_pantalla(estatus.getTamaño_pantalla());
			updateEquipo.setFecha_compra(estatus.getFecha_compra());
			updateEquipo.setLugar_compra(estatus.getLugar_compra());
			updateEquipo.setFecha_garantia_termino(estatus.getFecha_garantia_termino());
			updateEquipo.setFactura(estatus.getFactura());
			updateEquipo.setDireccion_mac_wifi(estatus.getDireccion_mac_wifi());
			mequiporepositorio.save(updateEquipo);
			return ResponseEntity.ok(updateEquipo);
			
		}else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
}
