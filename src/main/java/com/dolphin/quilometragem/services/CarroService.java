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
import com.dolphin.quilometragem.domain.Registro;
import com.dolphin.quilometragem.dto.CarroDTO;
import com.dolphin.quilometragem.dto.RegistroDTO;
import com.dolphin.quilometragem.repository.CarroRepository;
import com.dolphin.quilometragem.services.exceptions.ObjectNotFoundException;

@Service
public class CarroService{

	@Autowired
	private CarroRepository repo;
	
	
	public List< Carro > findAll(){
		return repo.findAll();
	}
	
	public Carro findById(String id) {
		Optional<Carro> obj = repo.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException(id) );
	}
	
	public Carro insert( Carro obj ) {
		return repo.insert(obj);
	}
	
	public Carro update( Carro obj ) {
		Carro carNew = repo.findById(obj.getId()).orElseThrow( () -> new ObjectNotFoundException(obj.getId()) );
		
		carNew.setModelo( obj.getModelo() );
		carNew.setAno( obj.getAno() );
		carNew.setCor( obj.getCor() );
		carNew.setMotorista( obj.getMotorista() );
		carNew.setObservacoes( obj.getObservacoes() );
		carNew.setRegistros( obj.getRegistros() );	

		return repo.save(carNew);
	}
	
	public void delete(String id) {
		Carro car = repo.findById(id).orElseThrow( () -> new ObjectNotFoundException(id) );
		
		if( car.getRegistros() != null ) {
			throw new InaccessibleObjectException(id);
		}
		
		repo.delete(car);
	}
	
	public List<RegistroDTO> searchRegistros(String id, String text, Date minDate, Date maxDate){
		Carro obj = repo.findById(id).orElseThrow( () -> new ObjectNotFoundException(id) );
		
		List<Registro> list = obj.getRegistros().stream().filter(
				x -> x.getDestino().toUpperCase().contains( text.toUpperCase()) &&
				( x.getHorario().after(minDate) ) && (x.getHorario().before(maxDate))
				).collect(Collectors.toList() );
		
		List<RegistroDTO> listDTO = new ArrayList<>();
		list.forEach( element -> listDTO.add(new RegistroDTO(element)) );
		
		return listDTO;
	}
	
	public Carro fromDTO( CarroDTO objDTO ) {
		return new Carro(objDTO.getId(), objDTO.getModelo(), objDTO.getAno(), objDTO.getCor(), objDTO.getPlaca());
	}
	
}
