package com.dolphin.quilometragem.dto;

import java.io.Serializable;

import com.dolphin.quilometragem.domain.Carro;
import com.dolphin.quilometragem.domain.enums.CarColor;

public class CarroDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String modelo;
	private String ano;
	private Integer cor;
	private String placa;
	
	private String motorista;
	
	public CarroDTO() {
		
	}
	
	public CarroDTO( Carro obj ) {
		this.id = obj.getId();
		this.modelo = obj.getModelo();
		this.ano = obj.getAno();
		this.setCor(obj.getCor());
		this.placa = obj.getPlaca();
		this.motorista = obj.getMotorista() != null ? obj.getMotorista().getId() : null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public CarColor getCor() {
		return CarColor.valueOf(this.cor);
	}

	public void setCor(CarColor cor) {
		if( cor != null ) {
			this.cor = cor.getCode();
		}
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMotorista() {
		return motorista;
	}

	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}
	
}
