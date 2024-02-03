package com.dolphin.quilometragem.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dolphin.quilometragem.domain.Carro;
import com.dolphin.quilometragem.dto.CarroDTO;
import com.dolphin.quilometragem.services.CarroService;

@RestController
@RequestMapping(value = "/carros")
public class CarroResource {

	@Autowired
	private CarroService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity< List<CarroDTO> > findAll() {

		List<Carro> list = service.findAll();
		
		List<CarroDTO> listDTO = new ArrayList<>();
		
		for( Carro car : list ) {
			listDTO.add(new CarroDTO(car));
		}

		return ResponseEntity.ok().body(listDTO);
	}
	
}
