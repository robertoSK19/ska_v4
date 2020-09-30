package com.ska.validadores;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ska.constantes.Constantes;
import com.ska.entity.MUsuario;
import com.ska.repository.RepositoryMUsuario;

public class validadorToken {
	
	public static final String admin = Constantes.rolAdministrador;
	public static final String consultor = Constantes.rolConsutor;
	public static final String operador = Constantes.rolOperador;
	public static final String tokenValido = Constantes.respTokenValido;
	@Autowired
	private RepositoryMUsuario usuario;
	
	String valorToken; 
	String respuesta;
	JSONParser parser;

	public String validarToken(String token) {
		System.out.println(token);
		String DatosUser = "";
		String valorToken = "";
		System.out.println(token);
		System.out.println(valorToken);
		String msn = "";
		valorToken = token;
		if (valorToken == null || valorToken == "") {
			System.out.println("vacio");
			msn = "token vacio";
		} else {
			byte [] barr = Base64.getDecoder().decode(valorToken); 
			DatosUser=new String(barr);
			JsonParser parser = new JsonParser();
			JsonElement jsonTree = parser.parse(DatosUser);
			System.out.println(jsonTree);
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			int id_user = Integer.valueOf(jsonObject.get("id").toString());
			String nombre = jsonObject.get("nombre").toString();
			String rol = jsonObject.get("rol").toString();
			System.out.println(jsonTree.isJsonObject());
			if(jsonTree.isJsonObject()) {
				System.out.println(rol +" "+ admin  +" "+ operador +" "+ consultor);
				if ( rol.equals(admin) || rol.equals(operador) || rol.equals(consultor) ) {
					msn = tokenValido;
				} else {
					msn = "Datos incorrectos";
				}
			} else {
				//return "Token invalido";
				msn = "token invalido";
			}
		}
		return msn;
	}
	
}
