package com.dolphin.quilometragem.dto;

import java.io.Serializable;
import java.util.Date;

import com.dolphin.quilometragem.domain.Registro;

public class RegistroDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String odometro;
	private String destino;
	private Date horario;
	
	//Id carro
	private String carro;
	//Id motorista
	private String motorista;
	
	public RegistroDTO() {
	}

	public RegistroDTO(Registro obj) {
		this.id = obj.getId();
		this.odometro = obj.getOdometro();
		this.destino = obj.getDestino();
		this.horario = obj.getHorario();
		this.carro = obj.getCarro().getId();
		this.motorista = obj.getMotorista().getId();
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

	public String getCarro() {
		return carro;
	}

	public void setCarro(String carro) {
		this.carro = carro;
	}

	public String getMotorista() {
		return motorista;
	}

	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}
	
}
