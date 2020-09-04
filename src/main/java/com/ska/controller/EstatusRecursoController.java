package com.ska.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ska.entity.EstatusRecurso;
import com.ska.repository.RepositoryEstatusRecurso;


@RestController
@RequestMapping("estatus")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
public class EstatusRecursoController {
	@Autowired
	private RepositoryEstatusRecurso estatusRecursoRepository;

	@GetMapping(value="/get")
	public ResponseEntity<List<EstatusRecurso>> VerEstatus() {
		List<EstatusRecurso> Roles = estatusRecursoRepository.findAll();
		return ResponseEntity.ok(Roles);
	}
	@RequestMapping(value="/get/{id}")
	public ResponseEntity<EstatusRecurso> EstatusId(@PathVariable("id") Long id){
		Optional<EstatusRecurso> idestatus = estatusRecursoRepository.findById(id);
		if(idestatus.isPresent()) {
			return ResponseEntity.ok(idestatus.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping(value="/post")
	public ResponseEntity<EstatusRecurso> CrearEstatus(@RequestBody EstatusRecurso estatus){
			EstatusRecurso nuevoestatus = estatusRecursoRepository.save(estatus) ;
			return ResponseEntity.ok(nuevoestatus);
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<EstatusRecurso> EliminarEstatus(@PathVariable("id")Long id){
		estatusRecursoRepository.deleteById(id);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping(value="/put")
	public ResponseEntity<EstatusRecurso> EditarEstatus(@RequestBody EstatusRecurso estatus){
		
		Optional<EstatusRecurso> optionalEstatus = estatusRecursoRepository.findById(estatus.getId_estatus());
		if(optionalEstatus.isPresent()) {
			EstatusRecurso updateEstatus = optionalEstatus.get();
			updateEstatus.setNombre_estatus(estatus.getNombre_estatus());
			estatusRecursoRepository.save(updateEstatus);
			return ResponseEntity.ok(updateEstatus);
			
		}else {
			return ResponseEntity.noContent().build();
		}
		
	}
}
