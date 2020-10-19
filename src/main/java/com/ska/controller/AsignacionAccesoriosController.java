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

import com.ska.entity.AsignacionAccesorios;
import com.ska.repository.RepositoryAsignacionAccesorios;
import com.ska.repository.RepositoryEstatusRecurso;

@RestController
@RequestMapping("/asignacionAccesorios")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class AsignacionAccesoriosController {
	
	@Autowired
	private RepositoryAsignacionAccesorios asignacionRepo;
	private AsignacionAccesorios asignacion;
	
	@Autowired
	private RepositoryEstatusRecurso estatusRepo;
	
	@RequestMapping(value = "/get")
	public ResponseEntity<List<AsignacionAccesorios>> VerAsignacionAccesorios(){
		List<AsignacionAccesorios> asignacionAccesorios = asignacionRepo.findAll();
		return ResponseEntity.ok(asignacionAccesorios);
	}
	
	@RequestMapping(value="")
	public ResponseEntity<AsignacionAccesorios> AsignacionAccesoriosId(@RequestParam("id") Long id){
		Optional<AsignacionAccesorios> idAsignacionAcce = asignacionRepo.findById(id);
		if(idAsignacionAcce.isPresent()) {
			return ResponseEntity.ok(idAsignacionAcce.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping(value="/post/{estatus_id}")
	public ResponseEntity<AsignacionAccesorios> CrearAsignacion(@PathVariable (value = "estatus_id") Long estatus_id,
			@RequestBody AsignacionAccesorios asignacion){
		this.asignacion = asignacion;
		
		estatusRepo.findById(estatus_id).map(status -> {
			this.asignacion.setEstatusrecurso(status);
			return this.asignacion;
		});
		
		AsignacionAccesorios nuevaAsignacion = asignacionRepo.save(asignacion);
		return ResponseEntity.ok(nuevaAsignacion);
	}
	
	@PutMapping
	public ResponseEntity<AsignacionAccesorios> EditarAccesorio(@RequestBody AsignacionAccesorios accesorio,
			@PathVariable(value = "id_estatus") Long id_estatus){
		
		Optional<AsignacionAccesorios> optionalAccesorio = asignacionRepo.findById(accesorio.getId_asignacion());
		if(optionalAccesorio.isPresent()) {
			AsignacionAccesorios updateAccesorio = optionalAccesorio.get();
			
			estatusRepo.findById(id_estatus).map(status -> {
				updateAccesorio.setEstatusrecurso(status);
				return updateAccesorio;
			});
			
			updateAccesorio.setNombre_consultor(accesorio.getNombre_consultor());
			updateAccesorio.setFecha_asignacion(accesorio.getFecha_asignacion());
			updateAccesorio.setCosto(accesorio.getCosto());
			updateAccesorio.setLetra(accesorio.getLetra());
			updateAccesorio.setUsuario(accesorio.getUsuario());
			
			asignacionRepo.save(updateAccesorio);
			return ResponseEntity.ok(updateAccesorio);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
