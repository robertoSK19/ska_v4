package com.ska.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ska.constantes.Constantes;
import com.ska.entity.MUsuario;
import com.ska.repository.RepositoryMUsuario;
import com.ska.repository.RepositoryRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ska.validadores.*;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE  })
public class MUsuarioController {

	public static final String nombreHeader = Constantes.nombreHeader;
	public static final String respToken = Constantes.respTokenValido;
	private static final Logger log = LoggerFactory.getLogger(MUsuarioController.class);
	public validadorToken validador= new validadorToken();
	@Autowired
	private RepositoryMUsuario usuario;
	private MUsuario muUsuario;

	@Autowired
	private RepositoryRoles repositoryRoles;
	
	HttpStatus codigo=null;
	
	@RequestMapping("/get") 
	public ResponseEntity<List<MUsuario>> VerUsuarios(@RequestHeader (nombreHeader) String token) {
		String resp=validador.validarToken(token);
		
		if (resp == respToken) {
			return ResponseEntity.ok(usuario.findAll());
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = "/get/{id}")
	public ResponseEntity<MUsuario> UsuarioId(@RequestHeader (nombreHeader) String token, @PathVariable("id") Long id  ) {
		String resp=validarToken(token);
		
		Optional<MUsuario> idUsuario = usuario.findById(id);
		if (idUsuario.isPresent() && resp == respToken) {
			return ResponseEntity.ok(idUsuario.get());
		} else if (idUsuario.isPresent() && resp != respToken) {
			return ResponseEntity.badRequest().build();
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
		 
		String msnR="";
		String msn = "";
		HttpStatus codigo=null;
		for(MUsuario u:ListaUser) {
			if(u.getCorreo().equals(correo)) {
				ifUser=true;
				break;
			}else {
				ifUser=false;
			}
		}
		
		String aux_pass="";
		if(ifUser==true) {
			for(MUsuario u:ListaUser) {
				if(u.getCorreo().equals(correo)) {
				aux_pass=getMD5(u.getContraseña());	
					if(aux_pass.equals(pass)) {
						log.info("acceso correcto");
						msnR=(u.getRol().getRol());
						JsonObject msnJ = new JsonObject();
						msnJ.addProperty("rol", msnR);
						msnJ.addProperty("nombre",u.getNombres());
						msnJ.addProperty("id",u.getId_usuario());

						msn = msnJ.toString();
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
	public ResponseEntity<MUsuario> CrearUsuario(@RequestHeader (nombreHeader) String token, @PathVariable(value = "roles_id") Long roles_id,
			@RequestBody MUsuario Usuario) {
		String resp=validarToken(token);
		if (resp == respToken) {
		System.out.println(roles_id);
		System.out.println(this.muUsuario);
		this.muUsuario = Usuario;
		repositoryRoles.findById(roles_id).map(u -> {
			this.muUsuario.setRol(u);
			return this.muUsuario;
		});
		MUsuario nuevoUsuario = usuario.save(Usuario);
		return ResponseEntity.ok(nuevoUsuario);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MUsuario> EliminarUsuario(@RequestHeader (nombreHeader) String token, @PathVariable("id") Long id) {
		String resp = validarToken(token);
		if (resp == respToken) {
		usuario.deleteById(id);
		return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping(value = "/put/{roles_id}")
	public ResponseEntity<MUsuario> EditarUsuario(@RequestHeader (nombreHeader) String token, @PathVariable(value = "roles_id") Long roles_id,
			@RequestBody MUsuario Usuario) {
		String resp = validarToken(token); 
		Optional<MUsuario> optionalUsuario = usuario.findById(Usuario.getId_usuario());
		if (optionalUsuario.isPresent() && resp == respToken) {
			this.muUsuario = Usuario;
			repositoryRoles.findById(roles_id).map(u -> {
				this.muUsuario.setRol(u);
				return this.muUsuario;
			});

			MUsuario nuevoUsuario = usuario.save(Usuario);
			return ResponseEntity.ok(nuevoUsuario);

		} else if (optionalUsuario.isPresent() && resp != respToken) {
			return ResponseEntity.badRequest().build();
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
	
	public String validarToken(String token) {
		String DatosUser = "";
		String valorToken = "";
		String msn = "";
		valorToken = token;
		if (valorToken == null || valorToken == "") {
			System.out.println("vacio");
			msn = "token vacio";
		} else {
			byte [] barr = Base64.getDecoder().decode(valorToken); 
			System.out.println("Decoded value is " + new String(barr));
			DatosUser=new String(barr);
			JsonParser parser = new JsonParser();
			
			JsonElement jsonTree = parser.parse(DatosUser);
			System.out.println(jsonTree);
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			int id_user = Integer.valueOf(jsonObject.get("id_user").toString());
			String nombre = jsonObject.get("nombre").toString().replace("\"", "");
			String rol = jsonObject.get("rol").toString().replace("\"", "");
			if(jsonTree.isJsonObject()) {
//				Optional<MUsuario> idUsuario = usuario.findById((long) id_user);
//				if (idUsuario.isPresent()) {
//					if (idUsuario.get().getNombres().equals(nombre) && idUsuario.get().getRol().getRol().equals(rol)) {
//						msn = respToken;
//					} else {
//						msn = "Datos Incorrectos";
//					}
//				} else {
//					msn = "No se encontro el usuario";
//				}
				
			} else {
				msn = "token invalido";
			}
		}
		return msn;
	}
	
}
