package com.dolphin.quilometragem.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dolphin.quilometragem.dto.CarroDTO;
import com.dolphin.quilometragem.dto.MotoristaDTO;

@Document(collection = "registro")
public class Registro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String odometro;
	private String destino;
	private String observation;
	private Date horario;
	
	private CarroDTO carro;
	private MotoristaDTO motorista;
	
	public Registro() {
	}

	public Registro(String id, String odometro, String destino, String observation, Date horario, CarroDTO carro,
			MotoristaDTO motorista) {
		this.id = id;
		this.odometro = odometro;
		this.destino = destino;
		this.observation = observation;
		this.horario = horario;
		this.carro = carro;
		this.motorista = motorista;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOdometro() {
		return odometro;
	}

	public void setOdometro(String odometro) {
		this.odometro = odometro;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public CarroDTO getCarro() {
		return carro;
	}

	public void setCarro(CarroDTO carro) {
		this.carro = carro;
	}

	public MotoristaDTO getMotorista() {
		return motorista;
	}

	public void setMotorista(MotoristaDTO motorista) {
		this.motorista = motorista;
	}
	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Registro [id=" + id + ", odometro=" + odometro + ", destino=" + destino + ", observation=" + observation
				+ ", horario=" + horario + ", carro=" + carro + ", motorista=" + motorista + "]";
	}
	
}
