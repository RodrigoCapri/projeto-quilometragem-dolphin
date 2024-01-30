package com.dolphin.quilometragem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dolphin.quilometragem.domain.Motorista;

@Repository
public interface MotoristaRepository extends MongoRepository<Motorista, String>{

}
