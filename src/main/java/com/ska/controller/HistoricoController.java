package com.ska.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ska.entity.Historico;
import com.ska.repository.RepositoryHistorico;

@RestController
@RequestMapping("historico")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
public class HistoricoController {
	
	@Autowired
	private RepositoryHistorico historicoRepositorio;
	
	@GetMapping(value="/get")
	public ResponseEntity<List<Historico>> VerHistorico(){
		List<Historico> historico = historicoRepositorio.findAll();
		return ResponseEntity.ok(historico);
	}
	
	@RequestMapping(value="/get/{id}")
	public ResponseEntity<Historico> HistoricoId(@PathVariable("id") Long id){
		Optional<Historico> idhistorico = historicoRepositorio.findById(id);
		if(idhistorico.isPresent()) {
			return ResponseEntity.ok(idhistorico.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
}
