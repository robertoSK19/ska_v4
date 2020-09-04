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
import com.ska.entity.Accesorio;
import com.ska.repository.RepositoryAccesorio;
import com.ska.repository.RepositoryEstatusRecurso;

@RestController
@RequestMapping("/accesorios")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class AccesorioController {
	@Autowired
	private RepositoryEstatusRecurso repoestatusRecurso;
	@Autowired
	private RepositoryAccesorio repoAccesorio;
	private Accesorio accesorio;

	// Lee toda la información de la tabla
	@RequestMapping("/get")
	public List<Accesorio> verAccesorios() {
		return repoAccesorio.findAll();
	}

	// Lee información mediante la ID de la tabla
	@RequestMapping(value = "/get/{id}")
	public ResponseEntity<Accesorio> AccesorioId(@PathVariable("id") Long id) {
		Optional<Accesorio> idAccesorio = repoAccesorio.findById(id);
		if (idAccesorio.isPresent()) {
			return ResponseEntity.ok(idAccesorio.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	// Guarda nueva información a la tabla
	@PostMapping(value = "/post/{estatus_id}")
	public ResponseEntity<Accesorio> CrearAccesorio(@PathVariable(value = "estatus_id") Long estatus_id,
			@RequestBody Accesorio accesorio) {
		this.accesorio = accesorio;

		repoestatusRecurso.findById(estatus_id).map(u -> {
			this.accesorio.setid_Estatus(u);
			;
			return this.accesorio;
		});

		Accesorio nuevoAccesorio = repoAccesorio.save(accesorio);
		return ResponseEntity.ok(nuevoAccesorio);
	}

	//Actualizar los campos de la tabla por ID
	@PutMapping(value = "/put/{id}")
	public ResponseEntity<Accesorio> EditarAccesorio(@PathVariable(value = "id") Long id,
			@RequestBody Accesorio accesorio) {

		Optional<Accesorio> optionalAccesorio = repoAccesorio.findById(accesorio.getId_accesorio());
		if (optionalAccesorio.isPresent()) {
			this.accesorio = accesorio;

			repoestatusRecurso.findById(id).map(u -> {
				this.accesorio.setid_Estatus(u);
				return this.accesorio;
			});

			Accesorio nuevoAccesorio = repoAccesorio.save(accesorio);
			return ResponseEntity.ok(nuevoAccesorio);

		} else {
			return ResponseEntity.noContent().build();
		}

	}

}
