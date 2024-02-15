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

import com.dolphin.quilometragem.domain.Registro;
import com.dolphin.quilometragem.dto.RegistroDTO;
import com.dolphin.quilometragem.services.RegistroService;

@RestController
@RequestMapping(value = "registros")
public class RegistroResource {
	
	@Autowired
	private RegistroService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity< List<RegistroDTO> > findAll(){
		
		List<Registro> list = service.findAll();
		List<RegistroDTO> listDTO = new ArrayList<>();
		list.forEach( element -> listDTO.add(new RegistroDTO(element)) );
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity< RegistroDTO > findById(@PathVariable String id){
		
		RegistroDTO objDTO = new RegistroDTO( service.findById(id) );
		
		return ResponseEntity.ok().body(objDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity< Void > insert(@RequestBody RegistroDTO objDTO) {
		
		Registro obj = service.fromDTO(objDTO);
		
		service.insert(obj);

		// Pegar o endereço do novo objeto inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}") // Para atualizar um registro
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody RegistroDTO objDTO) { // Casar o atributo com a requisição

		Registro obj = service.fromDTO(objDTO);
		obj.setId(id);
		
		service.update(obj);

		return ResponseEntity.noContent().build(); // 204 no content, não retorna nada
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
}
