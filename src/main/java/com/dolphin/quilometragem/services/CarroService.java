package com.dolphin.quilometragem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dolphin.quilometragem.domain.Carro;
import com.dolphin.quilometragem.dto.CarroDTO;
import com.dolphin.quilometragem.repository.CarroRepository;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carro_repo;
	
	public List< Carro > findAll(){
		return carro_repo.findAll();
	}
	
	public Carro findById(String id) {
		return carro_repo.findById(id).get();
	}
	
	public Carro insert( Carro obj ) {
		return carro_repo.insert(obj);
	}
	
	public Carro update( Carro obj ) {
		Carro carNew = carro_repo.findById(obj.getId()).get();
		
		carNew.setModelo( obj.getModelo() );
		carNew.setAno( obj.getAno() );
		carNew.setCor( obj.getCor() );
		//carNew.setMotorista( obj.getMotorista() );

		return carro_repo.save(carNew);
	}
	
	public void delete(String id) {
		carro_repo.delete( this.findById(id) );
	}
	
	public Carro fromDTO( CarroDTO objDTO ) {
		return new Carro(objDTO.getId(), objDTO.getModelo(), objDTO.getAno(), objDTO.getCor(), objDTO.getPlaca(), null);
	}
	
}
