package com.dolphin.quilometragem.services;

import java.lang.reflect.InaccessibleObjectException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dolphin.quilometragem.domain.Carro;
import com.dolphin.quilometragem.domain.Motorista;
import com.dolphin.quilometragem.domain.Registro;
import com.dolphin.quilometragem.dto.MotoristaDTO;
import com.dolphin.quilometragem.dto.MotoristaLoginDTO;
import com.dolphin.quilometragem.dto.RegistroDTO;
import com.dolphin.quilometragem.repository.CarroRepository;
import com.dolphin.quilometragem.repository.MotoristaRepository;
import com.dolphin.quilometragem.services.exceptions.InvalidAssociation;
import com.dolphin.quilometragem.services.exceptions.ObjectNotFoundException;

@Service
public class MotoristaService {

	@Autowired
	private MotoristaRepository repo;
	
	@Autowired
	private CarroRepository car_repo;
	
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
		Motorista mot = repo.insert(obj);
		
		//Atualiza a coleção Carro
		if( mot.getCarro() != null ) {
			Carro car = car_repo.findById(mot.getCarro().getId()).get();
			if( car.getMotorista() != null ) {
				throw new InvalidAssociation("Não pode associar mais de um carro a um motorista!");
			}
			car.setMotorista(new MotoristaDTO(mot));
			car_repo.save(car);
		}
		
		return mot;
	}
	
	public Motorista update(Motorista obj) {
		Motorista newObj = repo.findById(obj.getId()).get();
		
		newObj.setNome(obj.getNome() != null ? obj.getNome() : newObj.getNome());
		newObj.setEmail(obj.getEmail() != null ? obj.getEmail() : newObj.getEmail());
		newObj.setTelefone(obj.getTelefone() != null ? obj.getTelefone() : newObj.getTelefone());
		newObj.setCpf(obj.getCpf() != null ? obj.getCpf() : newObj.getCpf());
		newObj.setCarteira(obj.getCarteira() != null ? obj.getCarteira() : newObj.getCarteira());
		newObj.setCarro(obj.getCarro() != null ? obj.getCarro() : newObj.getCarro());
		
		if(newObj.getCarro() != null) {
			Carro car = car_repo.findById(newObj.getCarro().getId()).get();
			if( car.getMotorista() != null ) {
				throw new InvalidAssociation("Não pode associar mais de um carro a um motorista!");
			}
			car.setMotorista(new MotoristaDTO(newObj));
			car_repo.save(car);
		}

		return repo.save(newObj);
	}
	
	public Motorista updateLogin(MotoristaLoginDTO loginDTO) {
		Motorista newObj = repo.findByCpf(loginDTO.getCpf()).orElseThrow( () -> new ObjectNotFoundException(loginDTO.getCpf()) );
		
		newObj.setSenha(loginDTO.getSenha() != null ? loginDTO.getSenha() : newObj.getSenha());
		newObj.setNum_acesso(loginDTO.getNum_acesso() != null ? loginDTO.getNum_acesso() : newObj.getNum_acesso());
		
		return repo.save(newObj);
	}
	
	public void delete(String id) {
		Motorista mot = repo.findById(id).orElseThrow( () -> new ObjectNotFoundException(id) );
		
		if( mot.getCarro() != null ) {
			throw new InaccessibleObjectException(id);
		}
		
		repo.delete(mot);
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
	
	public Carro findCarro(String id) {
		Motorista mot = repo.findById(id).orElseThrow( () -> new ObjectNotFoundException(id) );
		if( mot.getCarro() == null ) {
			throw new ObjectNotFoundException("Carro não encontrado para este motorista "+id);
		}
		return mot.getCarro();
	}
	
	public Motorista fromDTO(MotoristaDTO objDTO) {
		Carro car = null;
		
		if( objDTO.getCarro() != null )
			car = car_repo.findById(objDTO.getCarro()).get();
		
		return new Motorista(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), objDTO.getTelefone(), objDTO.getCpf(), objDTO.getCarteira(), null, null , car);
	}
	
}
