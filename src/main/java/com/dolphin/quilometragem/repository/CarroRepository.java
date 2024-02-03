package com.dolphin.quilometragem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dolphin.quilometragem.domain.Carro;

public interface CarroRepository extends MongoRepository<Carro, String>{

}
