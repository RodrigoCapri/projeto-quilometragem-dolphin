package com.dolphin.quilometragem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dolphin.quilometragem.domain.Motorista;
import com.dolphin.quilometragem.repository.MotoristaRepository;

@Service
public class MotoristaService {

	@Autowired
	private MotoristaRepository repo;
	
	public List<Motorista> findAll(){
		return repo.findAll();
	}
	
}
