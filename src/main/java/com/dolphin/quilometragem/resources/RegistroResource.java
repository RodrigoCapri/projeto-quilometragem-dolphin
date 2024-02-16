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

import com.dolphin.quilometragem.domain.Registro;
import com.dolphin.quilometragem.dto.RegistroDTO;
import com.dolphin.quilometragem.resources.util.URL;
import com.dolphin.quilometragem.services.RegistroService;

@RestController
@RequestMapping(value = "registros")
public class RegistroResource {

	@Autowired
	private RegistroService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RegistroDTO>> findAll() {

		List<Registro> list = service.findAll();
		List<RegistroDTO> listDTO = new ArrayList<>();
		list.forEach(element -> listDTO.add(new RegistroDTO(element)));

		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<RegistroDTO> findById(@PathVariable String id) {

		RegistroDTO objDTO = new RegistroDTO(service.findById(id));

		return ResponseEntity.ok().body(objDTO);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody RegistroDTO objDTO) {

		Registro obj = service.fromDTO(objDTO);

		service.insert(obj);

		// Pegar o endereço do novo objeto inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}") // Para atualizar um registro
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody RegistroDTO objDTO) { // Casar o atributo
																									// com a requisição

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

	@RequestMapping(method = RequestMethod.GET, value = "/search")
	public ResponseEntity<List<RegistroDTO>> searchRegistros(
			@RequestParam(value = "destino", defaultValue = "") String destino,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {

		destino = URL.decodeParam(destino); // Texto do destino
		Date min = URL.convertDate(minDate, new Date(0L)); // Date minima existente
		Date max = URL.convertDate(maxDate, new Date()); // Data atual da maquina

		List<RegistroDTO> list = service.searchRegistros(destino, min, max);

		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/motorista/{id}")
	public ResponseEntity<List<RegistroDTO>> findByMotorista(@PathVariable String id) {

		List<RegistroDTO> list = service.findByMotorista(id);

		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/carro/{id}")
	public ResponseEntity<List<RegistroDTO>> findByCarro(@PathVariable String id) {

		List<RegistroDTO> list = service.findByCarro(id);

		return ResponseEntity.ok().body(list);

	}

}
