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
import com.ska.entity.Responsiva;
import com.ska.repository.RepositoryAsignacion;
import com.ska.repository.RepositoryResponsivas;

@RestController
@RequestMapping("/responsiva")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class ResponsivaController {

	@Autowired
	private RepositoryResponsivas repositoryresponsivas;
	private Responsiva responsiva;

	@Autowired
	private RepositoryAsignacion asignacionRepo;

	// Lee toda la información de la tabla
	@RequestMapping("/get")
	public List<Responsiva> VerUsuarios() {
		return repositoryresponsivas.findAll();
	}

	// Lee información mediante la ID de la tabla
	@RequestMapping(value = "/get/{id}")
	public ResponseEntity<Responsiva> ResponsivaId(@PathVariable("id") Long id) {
		Optional<Responsiva> idResponsiva = repositoryresponsivas.findById(id);
		if (idResponsiva.isPresent()) {
			return ResponseEntity.ok(idResponsiva.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	// Guarda nueva información a la tabla
	@PostMapping(value = "/post/{id_asignacion}")
	public ResponseEntity<Responsiva> CrearResponsiva(@PathVariable(value = "id_asignacion") Long id_asignacion,
			@RequestBody Responsiva responsiva) {

		this.responsiva = responsiva;

		asignacionRepo.findById(id_asignacion).map(deid -> {
			this.responsiva.setId_asignacion(deid);
			return this.responsiva;
		});

		Responsiva nuevaResponsiva = repositoryresponsivas.save(responsiva);
		return ResponseEntity.ok(nuevaResponsiva);
	}

	// Actualizar los campos de la tabla por ID
	@PutMapping(name = "/put/{id_asignacion}")
	public ResponseEntity<Responsiva> EditarResponsiva(@RequestBody Responsiva responsiva,
			@PathVariable(value = "id_dequipo") Long id_dequipo) {

		Optional<Responsiva> optionalResponsiva = repositoryresponsivas.findById(responsiva.getId_responsiva());
		if (optionalResponsiva.isPresent()) {
			this.responsiva = responsiva;

			asignacionRepo.findById(id_dequipo).map(u -> {
				this.responsiva.setId_asignacion(u);
				return this.responsiva;
			});

			Responsiva updateResponsiva = optionalResponsiva.get();
			updateResponsiva.setId_responsiva(responsiva.getId_responsiva());
			updateResponsiva.setEstatus_responsiva(responsiva.getEstatus_responsiva());
			updateResponsiva.setFecha_responsiva(responsiva.getFecha_responsiva());

			repositoryresponsivas.save(updateResponsiva);
			return ResponseEntity.ok(updateResponsiva);

		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
