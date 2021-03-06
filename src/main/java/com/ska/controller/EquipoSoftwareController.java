package com.ska.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ska.entity.EquipoSoftware;
import com.ska.repository.RepositoryEquipoSoftware;
import com.ska.repository.RepositoryHistorico;
import com.ska.repository.RepositoryMEquipo;
import com.ska.repository.RepositorySoftware;

@RestController
@RequestMapping("/equipoSoftware")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class EquipoSoftwareController {
	
	@Autowired
	private RepositoryEquipoSoftware repositorioEquipoSoftware;
	private EquipoSoftware equipoSoftware;
	
	@Autowired
	private RepositoryMEquipo repositorioMEquipo;
	
	@Autowired
	private RepositorySoftware repositorioSoftware;
	
	@Autowired
	private RepositoryHistorico repositorioHistorico;
	
	@RequestMapping("/get")
	public List<EquipoSoftware> VerEquipoSoftware(){
		return repositorioEquipoSoftware.findAll();
	}
	
	@RequestMapping(value = "post/{id_equipo},{id_software},{id_historico}")
	public ResponseEntity<EquipoSoftware> CrearEquipoSoftware(@PathVariable(value = "id_equipo") Long id_equipo,
			@PathVariable(value = "id_software") Long id_software, @PathVariable(value= "id_historico") Long id_historico, @RequestBody EquipoSoftware equipoSoftware) {
		
//		this.equipoSoftware = equipoSoftware;
//		repositorioMEquipo.findById(id_equipo).map(u -> {
//			this.equipoSoftware.setId_equipo(u);
//			return this.equipoSoftware;
//		});
//		
////		repositorioSoftware.findById(id_software).map(u -> {
//			this.equipoSoftware.setId_software(id_software);
////			return this.equipoSoftware;
////		});
//		
//		repositorioHistorico.findById(id_historico).map(u -> {
//			this.equipoSoftware.setId_historico(u);;
//			return this.equipoSoftware;
//		});
		
		EquipoSoftware nuevoEquipoSoftware = repositorioEquipoSoftware.save(equipoSoftware);
		return ResponseEntity.ok(nuevoEquipoSoftware);
	}
}
