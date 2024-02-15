package com.dolphin.quilometragem.services;

import java.lang.reflect.InaccessibleObjectException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dolphin.quilometragem.domain.Motorista;
import com.dolphin.quilometragem.domain.Registro;
import com.dolphin.quilometragem.dto.MotoristaDTO;
import com.dolphin.quilometragem.dto.MotoristaLoginDTO;
import com.dolphin.quilometragem.dto.RegistroDTO;
import com.dolphin.quilometragem.repository.MotoristaRepository;
import com.dolphin.quilometragem.services.exceptions.ObjectNotFoundException;

@Service
public class MotoristaService {

	@Autowired
	private MotoristaRepository repo;
	
	public List<Motorista> findAll(){
		return repo.findAll();
	}
	
	public Motorista findById(String id) {
		Optional<Motorista> obj = repo.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException(id) );
	}
	
	public Motorista findByLogin(MotoristaLoginDTO loginDTO) {
		Optional<Motorista> obj = repo.findByLogin(loginDTO.getCpf(), loginDTO.getSenha());
		return obj.orElseThrow( () -> new ObjectNotFoundException(loginDTO.getCpf()) );
	}
	
	public Motorista insert(Motorista obj) {
		return repo.insert(obj);
	}
	
	public Motorista update(Motorista obj) {
		Motorista newObj = repo.findById(obj.getId()).get();
		
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setCpf(obj.getCpf());
		newObj.setCarteira(obj.getCarteira());

		return repo.save(newObj);
	}
	
	public Motorista updateLogin(MotoristaLoginDTO loginDTO) {
		Motorista newObj = repo.findByLogin(loginDTO.getCpf(), loginDTO.getSenha()).orElseThrow( () -> new ObjectNotFoundException(loginDTO.getCpf()) );
		
		newObj.setSenha(loginDTO.getSenha());
		
		return repo.save(newObj);
	}
	
	public void delete(String id) {
		Motorista mot = repo.findById(id).orElseThrow( () -> new ObjectNotFoundException(id) );
		
		if( mot.getCarro() != null ) {
			throw new InaccessibleObjectException(id);
		}
		
		repo.delete(mot);
	}
	
	public Motorista fromDTO(MotoristaDTO objDTO) {
		return new Motorista(objDTO.getId(), objDTO.getNome(), objDTO.getCpf(), objDTO.getCarteira(), objDTO.getSenha());
	}

	public List<RegistroDTO> searchRegistros(String id, String text, Date min, Date max) {
		Motorista obj = repo.findById(id).orElseThrow( () -> new ObjectNotFoundException(id) );
		
		List<Registro> list = obj.getRegistros().stream().filter(
				x -> x.getDestino().toUpperCase().contains( text.toUpperCase()) &&
				( x.getHorario().after(min) ) && (x.getHorario().before(max))
				).collect(Collectors.toList() );
		
		List<RegistroDTO> listDTO = new ArrayList<>();
		list.forEach( element -> listDTO.add(new RegistroDTO(element)) );
		
		return listDTO;
	}
	
}
