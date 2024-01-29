package com.dolphin.quilometragem.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dolphin.quilometragem.domain.Motorista;

@RestController
@RequestMapping(value = "/motoristas")
public class MotoristaResource {
	
	public List<Motorista> findAll(){
		List<Motorista> list = new ArrayList<>();
		return list;
	}
	
}
