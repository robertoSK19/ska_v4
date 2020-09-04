package com.ska.controller;

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
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.ska.entity.DEquipo;
import com.ska.repository.RepositoryDEquipo;
import com.ska.repository.RepositoryEstatusRecurso;
import com.ska.repository.RepositoryMEquipo;

@RestController
@RequestMapping("/DEquipos")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class DEquipoController {
	@Autowired
	private RepositoryDEquipo DEquipoRepo;
	private DEquipo dEquipo;

	@Autowired
	private RepositoryMEquipo MEquipoRepo;

	@Autowired
	private RepositoryEstatusRecurso estaturecursoRepo;

	// Lee toda la información de la tabla
	@GetMapping(value = "/get")
	public ResponseEntity<List<DEquipo>> VerDEquipos() {
		List<DEquipo> DEquipo = DEquipoRepo.findAll();
		return ResponseEntity.ok(DEquipo);
	}

	// Lee información mediante la ID de la tabla
	@RequestMapping(value = "/get/{id}")
	public ResponseEntity<DEquipo> DEquiposId(@PathVariable("id") Long id) {
		Optional<DEquipo> idDequipo = DEquipoRepo.findById(id);
		if (idDequipo.isPresent()) {
			return ResponseEntity.ok(idDequipo.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	// Guarda nueva información a la tabla
	@PostMapping(value = "/post/{equipo_id},{estatus_id}")
	public ResponseEntity<DEquipo> CrearDEquipo(@PathVariable(value = "estatus_id") Long est_id,
			@PathVariable(value = "equipo_id") Long equipo_id, @RequestBody DEquipo dequipo) {

		// referencia a la tabla MEquipo
		this.dEquipo = dequipo;
		MEquipoRepo.findById(equipo_id).map(eid -> {
			this.dEquipo.setMequipo(eid);
			return this.dEquipo;
		});

		// referencia a la tabla estatusRecurso
		estaturecursoRepo.findById(est_id).map(esid -> {
			this.dEquipo.setEstatusRecurso(esid);
			return this.dEquipo;
		});

		DEquipo nuevoDEquipo = DEquipoRepo.save(dequipo);
		return ResponseEntity.ok(nuevoDEquipo);
	}

	// Actualizar los campos de la tabla por ID
	@PutMapping(value = "/put")
	public ResponseEntity<DEquipo> EditarDEquipo(@RequestBody DEquipo dequipo) {

		Optional<DEquipo> optionalUsuario = DEquipoRepo.findById(dequipo.getId_dequipo());
		if (optionalUsuario.isPresent()) {
			DEquipo updateDEquipo = optionalUsuario.get();
			updateDEquipo.setEstatusRecurso(dequipo.getEstatusRecurso());
			updateDEquipo.setFecha_actualizacion_estatus(dequipo.getFecha_actualizacion_estatus());
			updateDEquipo.setComentarios(dequipo.getComentarios());
			updateDEquipo.setDisco_duro_solido(dequipo.getDisco_duro_solido());
			// nota: no esta el atributo, de MEquipo, ya que este no cambiara mientras se
			// quiera cambiar DEquipo

			DEquipoRepo.save(updateDEquipo);
			return ResponseEntity.ok(updateDEquipo);

		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
