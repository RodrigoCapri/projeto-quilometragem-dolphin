package com.dolphin.quilometragem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dolphin.quilometragem.domain.Motorista;
import com.dolphin.quilometragem.dto.MotoristaDTO;
import com.dolphin.quilometragem.repository.MotoristaRepository;

@Service
public class MotoristaService {

	@Autowired
	private MotoristaRepository repo;
	
	public List<Motorista> findAll(){
		return repo.findAll();
	}
	
	public Motorista findById(String id) {
		return repo.findById(id).get();
	}
	
	public Motorista findByLogin(String cpf, String senha) {
		return repo.findByLogin(cpf, senha);
	}
	
	public Motorista insert(Motorista obj) {
		return repo.insert(obj);
	}
	
	public Motorista update(Motorista obj) {
		Motorista newObj = repo.findById(obj.getId()).get();
		
		newObj.setNome(obj.getNome());
		newObj.setCpf(obj.getCpf());
		newObj.setCarteira(obj.getCarteira());

		return repo.save(newObj);
	}
	
	public void delete(String id) {
		repo.delete( this.findById(id) );
	}
	
	public Motorista fromDTO(MotoristaDTO objDTO) {
		return new Motorista(objDTO.getId(), objDTO.getNome(), objDTO.getCpf(), objDTO.getCarteira());
	}
	
}
