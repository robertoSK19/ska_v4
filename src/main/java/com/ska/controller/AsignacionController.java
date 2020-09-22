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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ska.entity.Asignacion;
import com.ska.entity.DEquipo;
import com.ska.repository.RepositoryAsignacion;
import com.ska.repository.RepositoryDEquipo;
import com.ska.repository.RepositoryEstatusRecurso;

@RestController
@RequestMapping("/asignacion")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class AsignacionController {

	@Autowired
	private RepositoryAsignacion asignacionRepo;
	private Asignacion asignacion;

	@Autowired
	private RepositoryDEquipo dequipoRepo;
	private RepositoryDEquipo dequipoRepoCambio;

	@Autowired
	private RepositoryEstatusRecurso estatusRepo;

	// Lee toda la información de la tabla
	@RequestMapping(value ="/get", method = RequestMethod.GET)
	public List<Asignacion> VerUsuarios() {
		return asignacionRepo.findAll();
	}

	// Lee información mediante la ID de la tabla
	@RequestMapping(value = "/get/{id}")
	public ResponseEntity<Asignacion> AsignacionId(@PathVariable("id") Long id) {
		Optional<Asignacion> idAsignacion = asignacionRepo.findById(id);
		if (idAsignacion.isPresent()) {
			return ResponseEntity.ok(idAsignacion.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	// Guarda nueva información a la tabla
	/*@PostMapping(value = "/post/{dequipo_id},{estatus_id}")
	public ResponseEntity<Asignacion> CrearUsuario(@PathVariable(value = "dequipo_id") Long dequipo_id,
			@PathVariable(value = "estatus_id") Long estatus_id, @RequestBody Asignacion asignacion) {
*/
	@PostMapping(value = "/post")
	public ResponseEntity<Asignacion> CrearUsuario(@RequestParam("dequipo_id") Long dequipo_id,
			@RequestParam("estatus_id") Long estatus_id, @RequestBody Asignacion asignacion) {
		this.asignacion = asignacion;
		
		dequipoRepo.findById(dequipo_id).map(deid -> {
			this.asignacion.setDequipo(deid);
			return this.asignacion;
		});
		
		estatusRepo.findById(estatus_id).map(esid -> {
			this.asignacion.setEstatusrecurso(esid);
			return this.asignacion;
		});
		
		Asignacion nuevaAsignacion = asignacionRepo.save(asignacion);
		return ResponseEntity.ok(nuevaAsignacion);
	}
	
	// Actualizar los campos de la tabla por ID
		@PutMapping(value = "/put/{id_dequipo},{id_estatus}")
		public ResponseEntity<Asignacion> EditarAsignacion(@RequestBody Asignacion Asignacion,
				@PathVariable(value = "id_dequipo") Long id_dequipo,
				@PathVariable(value = "id_estatus") Long id_estatus) {

			Optional<Asignacion> optionalAsignacion = asignacionRepo.findById(Asignacion.getId_asignacion());
			if (optionalAsignacion.isPresent()) {
				Asignacion updateAsignacion = optionalAsignacion.get();
				//this.asignacion = Asignacion;

				dequipoRepo.findById(id_dequipo).map(u -> {
					updateAsignacion.setDequipo(u);
					return updateAsignacion;
				});

				estatusRepo.findById(id_estatus).map(estatus -> {
					updateAsignacion.setEstatusrecurso(estatus);
					return updateAsignacion;
				});

				//Asignacion updateAsignacion = optionalAsignacion.get();
				updateAsignacion.setNombre_consultor(Asignacion.getNombre_consultor());
				updateAsignacion.setFecha_asignacion(Asignacion.getFecha_asignacion());
				updateAsignacion.setCosto(Asignacion.getCosto());
				updateAsignacion.setLetra(Asignacion.getLetra());
				updateAsignacion.setUsuario(Asignacion.getUsuario());
					
				asignacionRepo.save(updateAsignacion);
				return ResponseEntity.ok(updateAsignacion);

			} else {
				return ResponseEntity.noContent().build();
			}

		}
//		// Actualizar los campos de la tabla por ID
//				@PutMapping(value = "/put")
//				public ResponseEntity<Asignacion> CancelarAsignacion(@RequestParam("id_asignacion") Long id_asignacion,
//						@RequestParam("id_dequipo") Long id_dequipo,
//						@RequestParam("id_estatus") Long id_estatus) {
//					Optional<Asignacion> optionalAsignacion = asignacionRepo.findById(id_asignacion);
//					Asignacion Asignacion = optionalAsignacion.get();
//					
//					System.out.println(optionalAsignacion);
//					System.out.println(dequipoRepoCambio);
//					if (optionalAsignacion.isPresent()) {
//						this.asignacion = optionalAsignacion.get();
//
//						dequipoRepo.findById(id_dequipo).map(u -> {
//							this.asignacion.setDequipo(u);
//							return this.asignacion;
//						});
//
//						estatusRepo.findById(id_estatus).map(u -> {
//							this.asignacion.setEstatusrecurso(u);
//							return this.asignacion;
//						});
//
////						Asignacion updateAsignacion = optionalAsignacion.get();
////						updateAsignacion.setNombre_consultor(Asignacion.getNombre_consultor());
////						updateAsignacion.setFecha_asignacion(Asignacion.getFecha_asignacion());
////						updateAsignacion.setCosto(Asignacion.getCosto());
////						updateAsignacion.setLetra(Asignacion.getLetra());
////						updateAsignacion.setUsuario(Asignacion.getUsuario());
//						this.asignacion.setNombre_consultor(this.asignacion.getNombre_consultor());
//						this.asignacion.setFecha_asignacion(this.asignacion.getFecha_asignacion());
//						this.asignacion.setCosto(this.asignacion.getCosto());
//						this.asignacion.setLetra(this.asignacion.getLetra());
//						this.asignacion.setUsuario(this.asignacion.getUsuario());
//							
//						Asignacion nuevaAsignacion = asignacionRepo.save(Asignacion);
//						return ResponseEntity.ok(Asignacion);
//					} else {
//						return ResponseEntity.noContent().build();
//					}
//
//				}

}
