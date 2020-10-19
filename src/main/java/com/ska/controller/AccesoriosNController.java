package com.ska.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ska.entity.AccesoriosN;
import com.ska.entity.AsignacionAccesorios;
import com.ska.repository.RepositoryAccesorio;
import com.ska.repository.RepositoryAccesoriosN;
import com.ska.repository.RepositoryAsignacionAccesorios;
import clasesid.AccesorioNPK;

@RestController
@RequestMapping("/accesoriosN")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class AccesoriosNController {

	@Autowired
	private RepositoryAccesoriosN repositorioAccesoriosN;
	private AccesoriosN accesoriosN;
	
	@Autowired
	private RepositoryAsignacionAccesorios repositorioAsignacion;
	
	@Autowired
	private RepositoryAccesorio repositorioAccesorio;
	
	@RequestMapping("/get")
	public List<AccesoriosN> VerAccesoriosN(){
		return repositorioAccesoriosN.findAll();
	}
	
	@RequestMapping(value="")
	public ResponseEntity<List<AccesoriosN>> AccesoriosNId(@RequestParam("id") Long id){
		List<AccesoriosN> lista = repositorioAccesoriosN.findAll();
		ArrayList<AccesoriosN> listaResp= new ArrayList<AccesoriosN>();
		AccesoriosN auxAccn = null; 
		for(AccesoriosN accn:lista) {
			if (accn.getId_asignacion().getId_asignacion() == id) {
				auxAccn = lista.get(lista.lastIndexOf(accn));
				auxAccn = null;
			}
		}
		
		if(listaResp.size() == 0) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(listaResp);
		}
		
	}
	
	@RequestMapping(value = "/post/{id_asignacion},{id_accesorio}")
	public ResponseEntity<AccesoriosN> CrearEquipoSoftware (@PathVariable(value = "id_asignacion") Long id_asignacion,
			@PathVariable(value = "id_accesorio") Long id_accesorio, @RequestBody AccesoriosN accesoriosN){
		this.accesoriosN = accesoriosN;
		
		repositorioAsignacion.findById(id_asignacion).map(asignacion -> {
			this.accesoriosN.setId_asignacion(asignacion);
			return this.accesoriosN;
		});
		
		repositorioAccesorio.findById(id_accesorio).map(accesorio -> {
			this.accesoriosN.setId_accesorio(accesorio);
			return this.accesoriosN;
		});
		
		AccesoriosN nuevoAccesorioN = repositorioAccesoriosN.save(accesoriosN);
		return ResponseEntity.ok(nuevoAccesorioN);
	}
}
