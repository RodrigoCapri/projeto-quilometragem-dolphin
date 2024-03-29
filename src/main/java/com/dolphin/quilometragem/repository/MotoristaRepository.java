package com.dolphin.quilometragem.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.dolphin.quilometragem.domain.Motorista;

@Repository
public interface MotoristaRepository extends MongoRepository<Motorista, String>{

	@Query("{ $and: [ { 'cpf': ?0 },  { 'senha': ?1 } ] }") 
	Optional<Motorista> findByLogin(String cpf, String senha);
	
	@Query("{ 'cpf': ?0}")
	Optional<Motorista> findByCpf(String cpf);
	
}
