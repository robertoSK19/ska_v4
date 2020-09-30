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
import com.ska.entity.Software;
import com.ska.repository.RepositoryDEquipo;
import com.ska.repository.RepositorySoftware;

@RestController
@RequestMapping("/software")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class SoftwareController {
	@Autowired
	private RepositorySoftware softwarerepo;
	private Software software;
	@Autowired
	private RepositoryDEquipo mequiporepositorio;

	
	// Lee toda la información de la tabla
	@GetMapping(value = "/get")
	public ResponseEntity<List<Software>> VerSotware() {
		List<Software> software = softwarerepo.findAll();
		return ResponseEntity.ok(software);
	}

	// Lee información mediante la ID de la tabla
	@RequestMapping(value = "/get/{id}")
	public ResponseEntity<Software> SoftwareId(@PathVariable("id") Long id) {
		Optional<Software> idSoftware = softwarerepo.findById(id);
		if (idSoftware.isPresent()) {
			return ResponseEntity.ok(idSoftware.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
