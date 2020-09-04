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
import org.springframework.web.bind.annotation.RestController;


import com.ska.entity.Software_tipo;
import com.ska.repository.RepositorySoftware_tipo;

@RestController
@RequestMapping("/software_tipo")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class Software_tipoController {

	@Autowired
	private RepositorySoftware_tipo software_tiporepo;
	private Software_tipo software_tipo;

	// Muestra toda la informacion
	@GetMapping(value = "/get")
	public ResponseEntity<List<Software_tipo>> VerSotware_tipo() {
		List<Software_tipo> software_tipo = software_tiporepo.findAll();
		return ResponseEntity.ok(software_tipo);
	}

	//
	@RequestMapping(value = "/get/{id}")
	public ResponseEntity<Software_tipo> Software_tipo(@PathVariable("id") Long id) {
		Optional<Software_tipo> software_tipo = software_tiporepo.findById(id);
		if (software_tipo.isPresent()) {
			return ResponseEntity.ok(software_tipo.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping(value = "/post")
	public ResponseEntity<Software_tipo> CrearSoftTipo(@RequestBody Software_tipo software_tipo) {
		this.software_tipo = software_tipo;
		
		Software_tipo nuevosoftware_tipo = software_tiporepo.save(software_tipo);
		return ResponseEntity.ok(nuevosoftware_tipo);
	}

	@PutMapping(value = "/put")
	public ResponseEntity<Software_tipo> EditarSoftTipo(@RequestBody Software_tipo software_tipo) {

		Optional<Software_tipo> opcionalsoftware_tipo = software_tiporepo.findById(software_tipo.getId_software_tipo());
		if (opcionalsoftware_tipo.isPresent()) {
			Software_tipo updatesoftware_tipo = opcionalsoftware_tipo.get();
			updatesoftware_tipo.setNombre_software_tipo(software_tipo.getNombre_software_tipo());
			software_tiporepo.save(updatesoftware_tipo);
			return ResponseEntity.ok(updatesoftware_tipo);

		} else {
			
			return ResponseEntity.noContent().build();
			
		}

	}

}
