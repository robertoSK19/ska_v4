package com.ska.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ska.entity.Accesorio;
import com.ska.repository.RepositoryAccesorio;
import com.ska.repository.RepositoryEstatusRecurso;

@RestController
@RequestMapping("/accesorios")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })

public class AccesorioController {
	private static final Logger log = LoggerFactory.getLogger(AccesorioController.class);
	@Autowired
	private RepositoryEstatusRecurso repoestatusRecurso;
	@Autowired
	private RepositoryAccesorio repoAccesorio;
	private Accesorio accesorio;

	// Lee toda la información de la tabla
	//@RequestMapping("/get")
	@RequestMapping(value = "/get",method = RequestMethod.GET)
	public List<Accesorio> verAccesorios() {
		List<Accesorio> ListaAccesorio = repoAccesorio.findAll();
		// log.info("tamñano: " + ListaAccesorio.size());
		
		return repoAccesorio.findAll();
	}

	// Lee información mediante la ID de la tabla
	//@RequestMapping(value = "/get/{id}")
	//public ResponseEntity<Accesorio> AccesorioId(@PathVariable("id") Long id) {
	@RequestMapping(value = "/getAccesorio", method = RequestMethod.GET)
	public ResponseEntity<Accesorio> AccesorioId(@RequestParam("id") Long id) {
		Optional<Accesorio> idAccesorio = repoAccesorio.findById(id);
		if (idAccesorio.isPresent()) {
			return ResponseEntity.ok(idAccesorio.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	// Request que regresa un accesorio, por el id de equipo
	@RequestMapping(value = "/getAccesorioEquipo", method = RequestMethod.GET)
	public ResponseEntity<Accesorio> AccesorioIdEquipo(@RequestParam("id") Long id) {
		List<Accesorio> ListaAccesorio = repoAccesorio.findAll();
		boolean ifAccesorioEquipo = false;
		Accesorio AccesorioEquipo= new Accesorio();
		Optional<Accesorio> idAccesorio = null;
		HttpStatus codigo=null;
		for(Accesorio acc:ListaAccesorio ) {
			if (acc.getId_equipo() != null) {
				if (acc.getId_equipo().equals(id)) {
					ifAccesorioEquipo = true;
					break;
				} else {
					ifAccesorioEquipo = false;
				}
			}
		}
		System.out.println(ifAccesorioEquipo);
		if (ifAccesorioEquipo == true) {
			for(Accesorio acc:ListaAccesorio ) {
				if (acc.getId_equipo() != null) {
					if (acc.getId_equipo().equals(id)) {
						idAccesorio=repoAccesorio.findById(acc.getId_accesorio());
						AccesorioEquipo= acc;
						codigo = HttpStatus.OK;
					}
				}
			}
		} else if (ifAccesorioEquipo == false) {
			codigo = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<Accesorio>(AccesorioEquipo, codigo);
	}
	
	// Guarda nueva información a la tabla
	/*@PostMapping(value = "/post/{estatus_id}")
	public ResponseEntity<Accesorio> CrearAccesorio(@PathVariable(value = "estatus_id") Long estatus_id,
			@RequestBody Accesorio accesorio) {
			*/
	@PostMapping(value = "/post")
	public ResponseEntity<Accesorio> CrearAccesorio(@RequestParam("estatus_id") Long estatus_id,
			@RequestBody Accesorio accesorio) {
		List<Accesorio> ListaAccesorio = repoAccesorio.findAll();
		int indiceAccesorio = 0;
		indiceAccesorio = ListaAccesorio.size()+1;
		this.accesorio = accesorio;

		repoestatusRecurso.findById(estatus_id).map(u -> {
			this.accesorio.setid_Estatus(u);
			;
			return this.accesorio;
		});
		this.accesorio.setNombre_accesorio("Accesorio" + indiceAccesorio);

		Accesorio nuevoAccesorio = repoAccesorio.save(accesorio);
		return ResponseEntity.ok(nuevoAccesorio);
	}

	//Actualizar los campos de la tabla por ID, cambiando el status
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
	
	@PutMapping(value = "/actualizarDatos")
	public ResponseEntity<Accesorio> EditarDatosAccesorio(@RequestParam("estatus_id") Long estatus_id, @RequestBody Accesorio accesorio) {

		Optional<Accesorio> optionalAccesorio = repoAccesorio.findById(accesorio.getId_accesorio());
		if (optionalAccesorio.isPresent()) {
			this.accesorio = accesorio;
			this.accesorio.setNombre_accesorio(this.accesorio.getNombre_accesorio());
			System.out.println(this.accesorio);
			this.accesorio.setMarca(this.accesorio.getMarca());
			this.accesorio.setModelo(this.accesorio.getModelo());
			this.accesorio.setProducto(this.accesorio.getProducto());
			this.accesorio.setHecho_en(this.accesorio.getHecho_en());
			this.accesorio.setSerie(this.accesorio.getSerie());
			repoestatusRecurso.findById(estatus_id).map(u -> {
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
