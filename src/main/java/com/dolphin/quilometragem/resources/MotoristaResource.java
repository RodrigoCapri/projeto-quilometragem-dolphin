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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dolphin.quilometragem.domain.Motorista;
import com.dolphin.quilometragem.dto.MotoristaDTO;
import com.dolphin.quilometragem.services.MotoristaService;

@RestController
@RequestMapping(value = "/motoristas")
public class MotoristaResource {

	@Autowired // Instancia automaticamente o objeto
	private MotoristaService mot_service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity< List<MotoristaDTO> > findAll() {

		List<Motorista> list = mot_service.findAll();
		List<MotoristaDTO> listDTO = new ArrayList<>();
		for( Motorista mot : list ) {
			listDTO.add(new MotoristaDTO(mot));
		}

		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity< Motorista > findById(@PathVariable String id) {

		Motorista obj = mot_service.findById(id);

		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Motorista obj) {
		
		obj = mot_service.insert(obj);

		// Pegar o endereço do novo objeto inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}") // Para atualizar um registro
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody Motorista obj) { // Casar o atributo com a requisição

		obj.setId(id);
		obj = mot_service.update(obj);

		return ResponseEntity.noContent().build(); // 204 no content, não retorna nada
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		mot_service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ResponseEntity<MotoristaDTO> findByLogin(
			@RequestParam(value = "cpf") String cpf,
			@RequestParam(value = "senha") String senha) {

		Motorista obj = mot_service.findByLogin(cpf, senha);

		return ResponseEntity.ok().body(new MotoristaDTO(obj));
	}
}
