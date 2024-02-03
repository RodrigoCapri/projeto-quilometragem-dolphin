package com.dolphin.quilometragem.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.dolphin.quilometragem.domain.Carro;
import com.dolphin.quilometragem.domain.Motorista;
import com.dolphin.quilometragem.repository.CarroRepository;
import com.dolphin.quilometragem.repository.MotoristaRepository;

//Classe de configuração inicial para execução de testes na base de dados

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private MotoristaRepository moto_repo;
	
	@Autowired
	private CarroRepository carro_repo;
	
	@Override
	public void run(String... args) throws Exception {
		
		moto_repo.deleteAll();
		
		//Aplicando novos motoristas na base de dados
		Motorista mot1 = new Motorista(null, "Alex Many", "00022233344", "147258369", "1234", "0");
		Motorista mot2 = new Motorista(null, "Debora Manta", "4443332221100", "369258147", "3322", "0");
		Motorista mot3 = new Motorista(null, "Everton Sabre", "11199988877", "789456123", "7733", "0");

		Carro car1 = new Carro(null, "UNO", "2010", "VERMELHO", "AB0036", mot1, null);
		Carro car2 = new Carro(null, "UNO", "2012", "BRANCO", "AL4416", mot3, null);
		
		mot1.setCarro(car1);
		mot3.setCarro(car2);
		
		moto_repo.saveAll(Arrays.asList(mot1, mot2, mot3));
		carro_repo.saveAll(Arrays.asList(car1, car2));
		
	}

}
