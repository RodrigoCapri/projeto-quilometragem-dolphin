package com.dolphin.quilometragem.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.dolphin.quilometragem.domain.Registro;


public interface RegistroRepository extends MongoRepository<Registro, String>{

	@Query(value = "{ 'motorista.id': ?0 }")
	List<Registro> findByMotorista(String id);
	
	@Query(value = "{ 'carro.id': ?0 }")
	List<Registro> findByCarro(String id);
	
	@Query(value = "{ $and:[ "
			+ "{ 'horario': { $gte: ?1 } },"
			+ "{ 'horario': { $lte: ?2 } },"
			+ "{ 'destino': { $regex: ?0 } } ]"
			+ " }")
	List<Registro> searchRegistros(String text, Date min, Date max);
	
}
