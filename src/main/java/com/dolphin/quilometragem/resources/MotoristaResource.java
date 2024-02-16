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

import com.dolphin.quilometragem.domain.Motorista;
import com.dolphin.quilometragem.domain.Registro;
import com.dolphin.quilometragem.dto.MotoristaDTO;
import com.dolphin.quilometragem.dto.MotoristaLoginDTO;
import com.dolphin.quilometragem.dto.RegistroDTO;
import com.dolphin.quilometragem.resources.util.URL;
import com.dolphin.quilometragem.services.MotoristaService;

@RestController
@RequestMapping(value = "/motoristas")
public class MotoristaResource {

	@Autowired // Instancia automaticamente o objeto
	private MotoristaService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity< List<MotoristaDTO> > findAll() {

		List<Motorista> list = service.findAll();
		
		List<MotoristaDTO> listDTO = new ArrayList<>();
		
		list.forEach( (n) -> listDTO.add( new MotoristaDTO(n) ) );

		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity< MotoristaDTO > findById(@PathVariable String id) {

		Motorista obj = service.findById(id);

		return ResponseEntity.ok().body(new MotoristaDTO(obj));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Motorista obj) {
		
		obj = service.insert(obj);

		// Pegar o endereço do novo objeto inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}") // Para atualizar um registro
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody MotoristaDTO objDTO) { // Casar o atributo com a requisição

		Motorista obj = service.fromDTO(objDTO);
		obj.setId(id);
		
		service.update(obj);

		return ResponseEntity.noContent().build(); // 204 no content, não retorna nada
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ResponseEntity< Motorista > findByLogin( @RequestBody MotoristaLoginDTO objDTO ) {
		
		Motorista obj = service.findByLogin(objDTO);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/login")
	public ResponseEntity< Void > updateLogin( @RequestBody MotoristaLoginDTO objDTO ) {
		
		service.updateLogin(objDTO);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/registros")
	public ResponseEntity< List<RegistroDTO> > findAllRegistros(@PathVariable String id){
		
		Motorista mot = service.findById(id);
		List<Registro> list = mot.getRegistros();
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
