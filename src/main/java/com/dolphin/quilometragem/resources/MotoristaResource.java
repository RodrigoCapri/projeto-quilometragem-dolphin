package com.dolphin.quilometragem.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dolphin.quilometragem.domain.Motorista;
import com.dolphin.quilometragem.services.MotoristaService;

@RestController
@RequestMapping(value = "/motoristas")
public class MotoristaResource {
	
	@Autowired
	private MotoristaService mot_service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity< List<Motorista> > findAll(){
		
		List<Motorista> list = mot_service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
}
