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
import com.dolphin.quilometragem.domain.enums.CarColor;
import com.dolphin.quilometragem.dto.CarroDTO;
import com.dolphin.quilometragem.dto.MotoristaDTO;
import com.dolphin.quilometragem.dto.RegistroDTO;
import com.dolphin.quilometragem.repository.CarroRepository;
import com.dolphin.quilometragem.repository.MotoristaRepository;
import com.dolphin.quilometragem.services.exceptions.InvalidAssociation;
import com.dolphin.quilometragem.services.exceptions.ObjectNotFoundException;

@Service
public class CarroService{

	@Autowired
	private CarroRepository repo;
	
	@Autowired
	private MotoristaRepository mot_repo;
	
	
	public List< Carro > findAll(){
		return repo.findAll();
	}
	
	public Carro findById(String id) {
		Optional<Carro> obj = repo.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException(id) );
	}
	
	public Carro insert( Carro obj ) {
		
		Carro new_carro = repo.insert(obj);
		
		if(new_carro.getMotorista() != null) {
			Motorista mot = mot_repo.findById(new_carro.getMotorista().getId()).get();
			if( mot.getCarro() != null ) {
				throw new InvalidAssociation("Não pode associar mais de um motorista num carro!");
			}
			mot.setCarro(new_carro);
			mot_repo.save(mot);
		}
		
		return new_carro;
	}
	
	public Carro update( Carro obj ) {
		Carro carNew = repo.findById(obj.getId()).orElseThrow( () -> new ObjectNotFoundException(obj.getId()) );
		
		carNew.setModelo( obj.getModelo() != null ? obj.getModelo() : carNew.getModelo() );
		carNew.setAno( obj.getAno() != null ? obj.getAno() : carNew.getAno() );
		carNew.setCor( obj.getCor() != null ? obj.getCor() : carNew.getCor());
		carNew.setMotorista( obj.getMotorista() != null ? obj.getMotorista() : carNew.getMotorista() );
		carNew.setRegistros( obj.getRegistros() != null ? obj.getRegistros() : carNew.getRegistros());	
		
		//Atualiza a coleção Motorista
		if( carNew.getMotorista().getId() != null ) {
			Motorista mot = mot_repo.findById(carNew.getMotorista().getId()).get();
			if( mot.getCarro() != null ) {
				throw new InvalidAssociation("Não pode associar mais de um motorista num carro!");
			}
			mot.setCarro(carNew);
			mot_repo.save(mot);
		}

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
	
	public MotoristaDTO findMotorista(String id) {
		Carro car = repo.findById(id).orElseThrow( () -> new ObjectNotFoundException(id) );
		
		if( car.getMotorista() == null )
			throw new ObjectNotFoundException("Motorista não encontrado para este carro "+car.getId());
		
		return car.getMotorista();
	}
	
	public List<CarColor> getCores(){
		List<CarColor> list = new ArrayList<>();
		for( CarColor cor : CarColor.values() ) {
			list.add(cor);
		}
		return list;
	}
	
	public Carro fromDTO( CarroDTO objDTO ) {
		MotoristaDTO mot = null;
		
		if(objDTO.getMotorista() != null)
			mot = new MotoristaDTO(mot_repo.findById(objDTO.getMotorista()).get());
			
		return new Carro(objDTO.getId(), objDTO.getModelo(), objDTO.getAno(), objDTO.getCor(), objDTO.getPlaca(), mot);
	}
	
}
