package com.ska.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ska.entity.MUsuario;
import com.ska.repository.RepositoryMUsuario;
import com.ska.repository.RepositoryRoles;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class MUsuarioController {
	
	@Autowired
	private RepositoryMUsuario usuario;
	private MUsuario muUsuario;

	@Autowired
	private RepositoryRoles repositoryRoles;

	@RequestMapping("/get")
	public List<MUsuario> VerUsuarios() {
		return usuario.findAll();
	}

	@RequestMapping(value = "/get/{id}")
	public ResponseEntity<MUsuario> UsuarioId(@PathVariable("id") Long id) {
		Optional<MUsuario> idUsuario = usuario.findById(id);
		if (idUsuario.isPresent()) {
			return ResponseEntity.ok(idUsuario.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping(value = "/post/{roles_id}")
	public ResponseEntity<MUsuario> CrearUsuario(@PathVariable(value = "roles_id") Long roles_id,
			@RequestBody MUsuario Usuario) {
		this.muUsuario = Usuario;
		repositoryRoles.findById(roles_id).map(u -> {
			this.muUsuario.setRol(u);
			return this.muUsuario;
		});
		MUsuario nuevoUsuario = usuario.save(Usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MUsuario> EliminarUsuario(@PathVariable("id") Long id) {
		usuario.deleteById(id);		
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/put/{roles_id}")
	public ResponseEntity<MUsuario> EditarUsuario(@PathVariable(value = "roles_id") Long roles_id,
			@RequestBody MUsuario Usuario) {

		Optional<MUsuario> optionalUsuario = usuario.findById(Usuario.getId_usuario());
		if (optionalUsuario.isPresent()) {
			this.muUsuario = Usuario;
			repositoryRoles.findById(roles_id).map(u -> {
				this.muUsuario.setRol(u);
				return this.muUsuario;
			});
			
			MUsuario nuevoUsuario = usuario.save(Usuario);
			return ResponseEntity.ok(nuevoUsuario);
			
		} else {
			
			return ResponseEntity.noContent().build();
			
		}

	}

}
