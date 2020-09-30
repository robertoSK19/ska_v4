package com.ska.controller;

import java.util.List;
import java.util.Optional;

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
	
	@Autowired
	private RepositoryMEquipo mequiporepositorio;
	
	@GetMapping(value="/get")
	public ResponseEntity<List<MEquipo>> VerMEquipoEstatus() {
		List<MEquipo> equipos = mequiporepositorio.findAll();
		return ResponseEntity.ok(equipos);
	}
	
	//@RequestMapping(value="{id}")
	@RequestMapping(value="")
	public ResponseEntity<MEquipo> MEquipoId(@RequestParam("id") Long id){
		Optional<MEquipo> idequipo = mequiporepositorio.findById(id);
		if(idequipo.isPresent()) {
			return ResponseEntity.ok(idequipo.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping(value="/post")
	public ResponseEntity<MEquipo> CrearEquipo(@RequestBody MEquipo equipo){
			MEquipo nuevoequipo = mequiporepositorio.save(equipo) ;
			return ResponseEntity.ok(nuevoequipo);
	}
	
	@PutMapping
	public ResponseEntity<MEquipo> EditarEquipo(@RequestBody MEquipo estatus){
		
		Optional<MEquipo> optionalEquipo = mequiporepositorio.findById(estatus.getId_equipo());
		if(optionalEquipo.isPresent()) {
			MEquipo updateEquipo = optionalEquipo.get();
			updateEquipo.setNombre_equipo(estatus.getNombre_equipo());
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
			mequiporepositorio.save(updateEquipo);
			return ResponseEntity.ok(updateEquipo);
			
		}else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
}
