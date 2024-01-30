package com.dolphin.quilometragem.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.dolphin.quilometragem.domain.Motorista;
import com.dolphin.quilometragem.repository.MotoristaRepository;

//Classe de configuração inicial para execução de testes na base de dados

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private MotoristaRepository moto_repo;
	
	@Override
	public void run(String... args) throws Exception {
		
		moto_repo.deleteAll();
		
		Motorista mot1 = new Motorista(null, "Alex Many", "00022233344", "147258369");
		Motorista mot2 = new Motorista(null, "Debora Manta", "4443332221100", "369258147");
		Motorista mot3 = new Motorista(null, "Everton Sabre", "11199988877", "789456123");
		
		moto_repo.saveAll(Arrays.asList(mot1, mot2, mot3));
		
	}

}
