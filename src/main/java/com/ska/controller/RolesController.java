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

import com.ska.entity.Roles;
import com.ska.repository.RepositoryRoles;

@RestController
@RequestMapping("roles")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
public class RolesController {
	@Autowired
	private RepositoryRoles roles;

	@GetMapping(value="/get")
	public ResponseEntity<List<Roles>> VerRoles() {
		List<Roles> Roles = roles.findAll();
		return ResponseEntity.ok(Roles);
	}
	
	@RequestMapping(value="/get/{id}")
	public ResponseEntity<Roles> RolesId(@PathVariable("id") Long id){
		Optional<Roles> idrol = roles.findById(id);
		if(idrol.isPresent()) {
			return ResponseEntity.ok(idrol.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping(value="/post")
	public ResponseEntity<Roles> CrearUsuario(@RequestBody Roles rol){
			Roles nuevorol = roles.save(rol) ;
			return ResponseEntity.ok(nuevorol);
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<Roles> EliminarRol(@PathVariable("id")Long id){
		roles.deleteById(id);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping(value="/put")
	public ResponseEntity<Roles> EditarRol(@RequestBody Roles rol){
		
		Optional<Roles> optionalRol = roles.findById(rol.getId_rol());
		if(optionalRol.isPresent()) {
			Roles updateRol = optionalRol.get();
			updateRol.setRol(rol.getRol());
			roles.save(updateRol);
			return ResponseEntity.ok(updateRol);			
		}else {
			return ResponseEntity.noContent().build();
		}
		
	}
}
