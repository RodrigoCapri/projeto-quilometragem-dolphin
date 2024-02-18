package com.dolphin.quilometragem.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.dolphin.quilometragem.domain.Carro;
import com.dolphin.quilometragem.domain.Motorista;
import com.dolphin.quilometragem.domain.Registro;
import com.dolphin.quilometragem.domain.enums.CarColor;
import com.dolphin.quilometragem.dto.CarroDTO;
import com.dolphin.quilometragem.dto.MotoristaDTO;
import com.dolphin.quilometragem.repository.CarroRepository;
import com.dolphin.quilometragem.repository.MotoristaRepository;
import com.dolphin.quilometragem.repository.RegistroRepository;

//Classe de configuração inicial para execução de testes na base de dados

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private MotoristaRepository moto_repo;

	@Autowired
	private CarroRepository carro_repo;

	@Autowired
	private RegistroRepository registro_repo;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		moto_repo.deleteAll();
		carro_repo.deleteAll();
		registro_repo.deleteAll();

		// Aplicando novos motoristas na base de dados
		Motorista mot1 = new Motorista(null, "Alex Many", "alex@gmail.com", "42977778888", "00033344481", "45678912", "1100", 0, null);
		Motorista mot2 = new Motorista(null, "Debora Manta", "debora@gmail.com", "41933334444", "99988834512", "32165455", "7958", 0, null);
		Motorista mot3 = new Motorista(null, "Everton Sabre", "everton@gmail.com", "1155551111", "14725896302", "96385211", "1532", 0, null);
		moto_repo.saveAll(Arrays.asList(mot1, mot2, mot3));

		// Aplicando carros na base de dados
		Carro car1 = new Carro(null, "UNO", "2010", CarColor.BRANCO, "AB0036", null);
		Carro car2 = new Carro(null, "UNO", "2012", CarColor.PRETO, "AL4416", null);
		carro_repo.saveAll(Arrays.asList(car1, car2));

		// Associando um carro ao motorista
		mot1.setCarro(car1);
		mot3.setCarro(car2);
		moto_repo.saveAll(Arrays.asList(mot1, mot3));

		// Aplicando registro na base de dados
		Registro reg1 = new Registro(null, "203415", "Maltaria", "N/A", sdf.parse("09/12/2023"), new CarroDTO(car1),
				new MotoristaDTO(mot1));
		Registro reg2 = new Registro(null, "203415", "Para casa", "N/A", sdf.parse("10/12/2023"), new CarroDTO(car1),
				new MotoristaDTO(mot1));
		Registro reg3 = new Registro(null, "203415", "Buscar colaboradores", "N/A", sdf.parse("11/12/2023"), new CarroDTO(car1),
				new MotoristaDTO(mot1));
		registro_repo.saveAll(Arrays.asList(reg1, reg2, reg3));

		// Associando um motorista ao carro
		car1.setMotorista(new MotoristaDTO(mot1));
		car2.setMotorista(new MotoristaDTO(mot3));
		// Associando registros ao carro
		car1.setRegistros(Arrays.asList(reg1, reg2, reg3));
		carro_repo.saveAll(Arrays.asList(car1, car2));
		
		mot1.setRegistros(Arrays.asList(reg1, reg2, reg3));
		moto_repo.save(mot1);

	}

}
