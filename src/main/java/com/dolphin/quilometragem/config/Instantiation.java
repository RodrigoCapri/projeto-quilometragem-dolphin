package com.dolphin.quilometragem.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.dolphin.quilometragem.domain.Carro;
import com.dolphin.quilometragem.domain.Motorista;
import com.dolphin.quilometragem.dto.MotoristaDTO;
import com.dolphin.quilometragem.dto.QuilometragemDTO;
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		moto_repo.deleteAll();
		carro_repo.deleteAll();
		
		//Aplicando novos motoristas na base de dados
		Motorista mot1 = new Motorista(null, "Alex Many", "00022233344", "147258369", "1234", "0");
		Motorista mot2 = new Motorista(null, "Debora Manta", "4443332221100", "369258147", "3322", "0");
		Motorista mot3 = new Motorista(null, "Everton Sabre", "11199988877", "789456123", "7733", "0");

		moto_repo.saveAll(Arrays.asList(mot1, mot2, mot3));
		
		Carro car1 = new Carro(null, "UNO", "2010", "VERMELHO", "AB0036", new MotoristaDTO(mot1), null);
		Carro car2 = new Carro(null, "UNO", "2012", "BRANCO", "AL4416", new MotoristaDTO(mot3), null);
		
		car1.getQuilometragem().add(new QuilometragemDTO("3302", "Para casa", sdf.parse("21/12/2023")));
		car1.getQuilometragem().add(new QuilometragemDTO("3310", "Empresa", sdf.parse("22/12/2023")));
		car1.getQuilometragem().add(new QuilometragemDTO("3350", "Buscar EPEis", sdf.parse("23/12/2023")));
		
		carro_repo.saveAll(Arrays.asList(car1, car2));
		
		mot1.setCarro(car1);
		mot3.setCarro(car2);
		
		moto_repo.saveAll(Arrays.asList(mot1, mot2, mot3));
		
	}

}
