package com.dolphin.quilometragem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dolphin.quilometragem.domain.Registro;

public interface RegistroRepository extends MongoRepository<Registro, String>{

	
}
