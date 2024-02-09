package com.dolphin.quilometragem.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dolphin.quilometragem.domain.Carro;
import com.dolphin.quilometragem.dto.CarroDTO;
import com.dolphin.quilometragem.dto.QuilometragemDTO;
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity< Carro > findById(@PathVariable String id) {

		Carro obj = service.findById(id);

		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity< Void > insert(@RequestBody Carro obj) {
		
		obj = service.insert(obj);

		// Pegar o endereço do novo objeto inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}") // Para atualizar um registro
	public ResponseEntity< Void > update(@PathVariable String id, @RequestBody Carro obj) { // Casar o atributo com a requisição

		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build(); // 204 no content, não retorna nada
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity< Void > delete(@PathVariable String id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/odometros")
	public ResponseEntity< Void > insertRegistroOdometro(@PathVariable String id, @RequestBody QuilometragemDTO quilometragem ){
		
		Carro car = service.findById(id);
		
		List<QuilometragemDTO> list = car.getQuilometragem();
		list.add(quilometragem);
		
		car.setQuilometragem(list);
		
		service.update(car);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/odometros")
	public ResponseEntity< List<QuilometragemDTO> > findAllOdometros(@PathVariable String id){
		
		Carro car = service.findById(id);
		
		List<QuilometragemDTO> list = car.getQuilometragem();
		
		return ResponseEntity.ok().body(list);
	}
	
}
