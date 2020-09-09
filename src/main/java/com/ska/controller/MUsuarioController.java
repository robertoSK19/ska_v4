package com.ska.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ska.entity.MUsuario;
import com.ska.repository.RepositoryMUsuario;
import com.ska.repository.RepositoryRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class MUsuarioController {

	private static final Logger log = LoggerFactory.getLogger(MUsuarioController.class);
	@Autowired
	private RepositoryMUsuario usuario;
	private MUsuario muUsuario;

	@Autowired
	private RepositoryRoles repositoryRoles;

	@RequestMapping("/get")
	public List<MUsuario> VerUsuarios() {
		log.info("todos los usuarios");
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

	//@RequestMapping(value = "/con_user/{correo},{password}")
	@GetMapping(value = "/con_user")
	//public ResponseEntity<String> ConsultaRoles(@PathVariable("correo") String correo, @PathVariable("password") String pass) {
	public ResponseEntity<String> ConsultaRoles(@RequestParam("correo") String correo, @RequestParam("password") String pass) {
		
		List<MUsuario> ListaUser=usuario.findAll();
		boolean ifUser= false;
		
		Gson gson = new Gson();
		 
		String msn="";
		HttpStatus codigo=null;
		for(MUsuario u:ListaUser) {
			if(u.getCorreo().equals(correo)) {
				ifUser=true;
				break;
			}else {
				ifUser=false;
				//log.info("usuario o constraseña incorrectos");
				//return new ResponseEntity<>("Usuario incorrecto",HttpStatus.NO_CONTENT);
			}
		}
		
		String aux_pass="";
		if(ifUser==true) {
			for(MUsuario u:ListaUser) {
				if(u.getCorreo().equals(correo)) {
				aux_pass=getMD5(u.getContraseña());	
					if(aux_pass.equals(pass)) {
						log.info("acceso correcto");
						msn=gson.toJson(u.getRol());
						codigo = HttpStatus.OK;
						//return ResponseEntity.ok(gson.toJson(u.getRol()));
					}
					else {
						log.info("contraseña incorrecta");
						msn="contraseña incorrecta";
						codigo = HttpStatus.NO_CONTENT;
						//return new ResponseEntity<>("contrasela incorrecta",HttpStatus.NO_CONTENT);
					}
				}
			}
		}else if(ifUser==false) {
			msn="usuario incorrecto";
			codigo = HttpStatus.NO_CONTENT;
			//return new ResponseEntity<>("Usuario incorrecto",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(msn, codigo);
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
	
	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
