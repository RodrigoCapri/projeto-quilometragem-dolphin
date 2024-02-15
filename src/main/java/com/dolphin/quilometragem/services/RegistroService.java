package com.dolphin.quilometragem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dolphin.quilometragem.domain.Carro;
import com.dolphin.quilometragem.domain.Motorista;
import com.dolphin.quilometragem.domain.Registro;
import com.dolphin.quilometragem.dto.CarroDTO;
import com.dolphin.quilometragem.dto.MotoristaDTO;
import com.dolphin.quilometragem.dto.RegistroDTO;
import com.dolphin.quilometragem.repository.CarroRepository;
import com.dolphin.quilometragem.repository.MotoristaRepository;
import com.dolphin.quilometragem.repository.RegistroRepository;
import com.dolphin.quilometragem.services.exceptions.ObjectNotFoundException;

@Service
public class RegistroService {

	@Autowired
	private RegistroRepository repo;
	@Autowired
	private CarroRepository car_repo;
	@Autowired
	private MotoristaRepository mot_repo;

	public List<Registro> findAll() {
		return repo.findAll();
	}

	public Registro findById(String id) {
		Optional<Registro> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}

	public Registro insert(Registro obj) {

		Registro registro = repo.insert(obj);

		List<Registro> list = new ArrayList<>();

		// Atualiza um novo registro para o Carro
		Carro car = car_repo.findById(registro.getCarro().getId()).get();
		list = car.getRegistros();
		list.add(registro);
		car.setRegistros(list);
		car_repo.save(car);

		// Atualiza um novo registro para o Carro
		Motorista mot = mot_repo.findById(registro.getMotorista().getId()).get();
		list = mot.getRegistros();
		list.add(registro);
		mot.setRegistros(list);
		mot_repo.save(mot);

		return registro;
	}

	public Registro update(Registro obj) {
		Registro newReg = repo.findById(obj.getId()).orElseThrow(() -> new ObjectNotFoundException(obj.getId()));

		List<Registro> list = new ArrayList<>();
		Registro regAnt = null;

		newReg.setDestino(obj.getDestino());
		newReg.setHorario(obj.getHorario());
		newReg.setOdometro(obj.getOdometro());
		newReg.setCarro(obj.getCarro());
		newReg.setMotorista(obj.getMotorista());

		// Atualizando o registro no Carro
		// Pega o carro segundo o id especificado no Registro
		Carro car = car_repo.findById(newReg.getCarro().getId()).orElseThrow(() -> new ObjectNotFoundException(newReg.getId())); 
		list = car.getRegistros(); // Pega a lista de registros atual do Carro
		// Filtra os registros para o que tem o id epecificado
		regAnt = list.stream().filter(x -> (x.getId().equals(newReg.getId()))).findAny().get(); 
		list.set(list.indexOf(regAnt), newReg); // Atualiza o registro no mesmo indice do objeto antigo
		car.setRegistros(list); // Atualiza os registros do carro novamente
		car_repo.save(car); // Salva as alterações feitas
		
		// Atualizando o registro no Motorista
		// Pega o carro segundo o id especificado no Registro
		Motorista mot = mot_repo.findById(newReg.getMotorista().getId()).orElseThrow(() -> new ObjectNotFoundException(newReg.getId())); 
		list = mot.getRegistros(); // Pega a lista de registros atual do Carro
		// Filtra os registros para o que tem o id epecificado
		regAnt = list.stream().filter(x -> (x.getId().equals(newReg.getId()))).findAny().get(); 
		list.set(list.indexOf(regAnt), newReg); // Atualiza o registro no mesmo indice do objeto antigo
		mot.setRegistros(list); // Atualiza os registros do carro novamente
		mot_repo.save(mot); // Salva as alterações feitas

		return repo.save(newReg);
	}

	public void delete(String id) {
		Registro registro = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));

		Carro car = car_repo.findById(registro.getCarro().getId()).orElseThrow(() -> new ObjectNotFoundException(registro.getId())); 
		List<Registro> list_reg_car = car.getRegistros(); // Pega a lista de registros atual do Carro
		// Filtra os registros para o que tem o id epecificado
		Registro reg_car = list_reg_car.stream().filter(x -> (x.getId().equals(registro.getId()))).findAny().get(); 
		list_reg_car.remove(reg_car);
		car.setRegistros(list_reg_car);
		car_repo.save(car);
		
		Motorista mot = mot_repo.findById(registro.getMotorista().getId()).orElseThrow(() -> new ObjectNotFoundException(registro.getId())); 
		List<Registro> list_reg_mot= mot.getRegistros(); // Pega a lista de registros atual do Carro
		// Filtra os registros para o que tem o id epecificado
		Registro reg_mot = list_reg_mot.stream().filter(x -> (x.getId().equals(registro.getId()))).findAny().get(); 
		list_reg_mot.remove(reg_mot);
		mot.setRegistros(list_reg_mot);
		mot_repo.save(mot);
		
		repo.delete(registro);
	}

	public Registro fromDTO(RegistroDTO objDTO) {
		MotoristaDTO mot = new MotoristaDTO(mot_repo.findById(objDTO.getMotorista()).get());
		CarroDTO car = new CarroDTO(car_repo.findById(objDTO.getCarro()).get());
		return new Registro(objDTO.getId(), objDTO.getOdometro(), objDTO.getDestino(), objDTO.getHorario(), car, mot);
	}

}
