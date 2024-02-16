package com.dolphin.quilometragem.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dolphin.quilometragem.domain.Carro;
import com.dolphin.quilometragem.domain.Registro;
import com.dolphin.quilometragem.dto.CarroDTO;
import com.dolphin.quilometragem.dto.RegistroDTO;
import com.dolphin.quilometragem.resources.util.URL;
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
		
		//Para cada objeto na lista carro, adiciona o mesmo na lista CarroDTO
		list.forEach( (n) -> listDTO.add(new CarroDTO(n)) );

		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity< CarroDTO > findById(@PathVariable String id) {

		Carro obj = service.findById(id);

		return ResponseEntity.ok().body(new CarroDTO(obj));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity< Void > insert(@RequestBody CarroDTO objDTO) {
		
		Carro obj = service.fromDTO(objDTO);
		obj = service.insert(obj);

		// Pegar o endereço do novo objeto inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}") // Para atualizar um registro
	public ResponseEntity< Void > update(@PathVariable String id, @RequestBody CarroDTO objDTO) { // Casar o atributo com a requisição
		
		Carro obj = service.fromDTO(objDTO);
		
		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build(); // 204 no content, não retorna nada
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity< Void > delete(@PathVariable String id) {
		
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/registros")
	public ResponseEntity< List<RegistroDTO> > findAllRegistros(@PathVariable String id){
		
		Carro car = service.findById(id);
		List<Registro> list = car.getRegistros();
		List<RegistroDTO> listDTO = new ArrayList<>();
		list.forEach( element -> listDTO.add(new RegistroDTO(element)) );
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/search")
	public ResponseEntity< List<RegistroDTO> > searchRegistros(@PathVariable String id,
			@RequestParam(value = "destino", defaultValue = "") String destino,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate){
		
		destino = URL.decodeParam(destino);
		Date min = URL.convertDate(minDate, new Date(0L)); //Date minima existente
		Date max = URL.convertDate(maxDate, new Date());  //Data atual da maquina
		
		List<RegistroDTO> list = service.searchRegistros(id, destino, min, max);
		
		return ResponseEntity.ok().body(list);
	}
	
	
}
