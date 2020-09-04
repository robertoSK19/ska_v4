package com.ska.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ska.entity.AAccesorio;
import com.ska.repository.RepositoryAAccesorio;
import com.ska.repository.RepositoryAccesorio;
import com.ska.repository.RepositoryAsignacion;

@RestController
@RequestMapping("/aaccesorios")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class AAccesoriosController {

	@Autowired
	private RepositoryAAccesorio repositoryAAccesorio;
	private AAccesorio aaccesorio;

	@Autowired
	private RepositoryAccesorio repositoryAccesorio;

	@Autowired
	private RepositoryAsignacion repositoryAsignacion;

	// Lee toda la información de la tabla
	@RequestMapping("/get")
	public List<AAccesorio> verAAccesorios() {
		return repositoryAAccesorio.findAll();
	}

	// Lee información mediante la ID de la tabla
	@RequestMapping(value = "/get/{id}")
	public ResponseEntity<AAccesorio> verAAcesorios(@PathVariable("id") Long id) {
		Optional<AAccesorio> idAccesorio = repositoryAAccesorio.findById(id);
		if (idAccesorio.isPresent()) {
			return ResponseEntity.ok(idAccesorio.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	// Guarda nueva información a la tabla
	@PostMapping(value = "/post/{accesorio_id},{asignacion_id}")
	public ResponseEntity<AAccesorio> CrearAccesorio(@PathVariable(value = "accesorio_id") Long accesorio_id,
			@PathVariable(value = "asignacion_id") Long asignacion_id, @RequestBody AAccesorio aaccesorio) {

		this.aaccesorio = aaccesorio;
		repositoryAccesorio.findById(accesorio_id).map(u -> {
			this.aaccesorio.setAccesorio(u);
			return this.aaccesorio;
		});

		repositoryAsignacion.findById(asignacion_id).map(u -> {
			this.aaccesorio.setAsignacion(u);
			return this.aaccesorio;
		});

		AAccesorio nuevoAAccesorio = repositoryAAccesorio.save(aaccesorio);
		return ResponseEntity.ok(nuevoAAccesorio);
	}

	// Actualizar los campos de la tabla por ID
	@PutMapping(value = "/put/{id_accesorio},{id_asignacion}")
	public ResponseEntity<AAccesorio> EditarAAccesorio(@PathVariable(value = "id_accesorio") Long id_accesorio,
			@PathVariable(value = "id_asignacion") Long id_asignacion, @RequestBody AAccesorio aaccesorio) {

		Optional<AAccesorio> optionalAAccesorio = repositoryAAccesorio.findById(aaccesorio.getId_aaccesorio());
		if (optionalAAccesorio.isPresent()) {

			this.aaccesorio = aaccesorio;
			repositoryAccesorio.findById(id_accesorio).map(u -> {
				this.aaccesorio.setAccesorio(u);
				return this.aaccesorio;
			});
			
			this.aaccesorio = aaccesorio;
			repositoryAsignacion.findById(id_asignacion).map(u -> {
				this.aaccesorio.setAsignacion(u);
				return this.aaccesorio;
			});

			AAccesorio nuevoAAccesorio = repositoryAAccesorio.save(aaccesorio);
			return ResponseEntity.ok(nuevoAAccesorio);

		} else {
			return ResponseEntity.noContent().build();
		}
	}

}
