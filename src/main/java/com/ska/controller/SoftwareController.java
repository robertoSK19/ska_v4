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
import com.ska.repository.RepositorySoftware_tipo;

@RestController
@RequestMapping("/software")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class SoftwareController {
	@Autowired
	private RepositorySoftware softwarerepo;
	private Software software;
	@Autowired
	private RepositoryDEquipo mequiporepositorio;

	@Autowired
	private RepositorySoftware_tipo software_tiporepo;

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

	// Guarda nueva información a la tabla
	@PostMapping(value = "/post/{id_dequipo},{id_software_tipo}")
	public ResponseEntity<Software> CrearSoftware(@PathVariable(value = "id_dequipo") Long id_dequipo,
			@PathVariable(value = "id_software_tipo") Long id_software_tipo, @RequestBody Software software) {
		
			this.software = software;
		mequiporepositorio.findById(id_dequipo).map(u -> {
			this.software.setId_dequipo(u);
			return this.software;
		});

		software_tiporepo.findById(id_software_tipo).map(u -> {
			this.software.setId_software_tipo(u);
			return this.software;
		});
		
		Software nuevoSoft = softwarerepo.save(software);
		return ResponseEntity.ok(nuevoSoft);
	}// NOTA:La fecha en el Json debe estar separada por giones yyyy-mm-dd

	// Actualizar los campos de la tabla por ID
	@PutMapping(name = "/put/{id_dequipo},{id_software_tipo}")
	public ResponseEntity<Software> EditarSoftware(@RequestBody Software Software,
			@PathVariable(value = "id_dequipo") Long id_dequipo,
			@PathVariable(value = "id_software_tipo") Long id_software_tipo) {

		Optional<Software> optionalSoftware = softwarerepo.findById(Software.getId_software());
		if (optionalSoftware.isPresent()) {
			this.software = Software;

			mequiporepositorio.findById(id_dequipo).map(u -> {
				this.software.setId_dequipo(u);
				return this.software;
			});

			software_tiporepo.findById(id_software_tipo).map(u -> {
				this.software.setId_software_tipo(u);
				return this.software;
			});

			Software updateSoftware = optionalSoftware.get();
			updateSoftware.setNombre_software(Software.getNombre_software());

			softwarerepo.save(updateSoftware);
			return ResponseEntity.ok(updateSoftware);

		} else {
			return ResponseEntity.noContent().build();
		}

	}
}
