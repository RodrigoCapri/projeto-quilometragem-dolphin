package com.dolphin.quilometragem.dto;

import com.dolphin.quilometragem.domain.Carro;

public class CarroDTO {

	private String id;
	private String modelo;
	private String ano;
	private String cor;
	private String placa;
	
	public CarroDTO( Carro obj ) {
		this.id = obj.getId();
		this.modelo = obj.getModelo();
		this.ano = obj.getAno();
		this.cor = obj.getCor();
		this.placa = obj.getPlaca();
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

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	@Override
	public String toString() {
		return "CarroDTO [id=" + id + ", modelo=" + modelo + ", ano=" + ano + ", cor=" + cor + ", placa=" + placa + "]";
	}

	
	
}
