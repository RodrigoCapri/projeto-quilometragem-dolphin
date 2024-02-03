package com.dolphin.quilometragem.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carro")
public class Carro implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String modelo;
	private String ano;
	private String cor;
	private String placa;
	
	private List<String> observacoes = new ArrayList<>();
	
	//@DBRef (lazy = true) //Para não carregar automaticamente os posts quando recuperar um usuário
	//private Motorista motorista;
	
	public Carro() {
	}

	public Carro(String id, String modelo, String ano, String cor, String placa, Motorista motorista, List<String> observacoes) {
		this.id = id;
		this.modelo = modelo;
		this.ano = ano;
		this.cor = cor;
		this.placa = placa;
		this.observacoes = observacoes;
		//this.motorista = motorista;
	}
	
	public Carro(String id, String modelo, String ano, String cor, String placa, List<String> observacoes) {
		this.id = id;
		this.modelo = modelo;
		this.ano = ano;
		this.cor = cor;
		this.placa = placa;
		this.observacoes = observacoes;
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

	public List<String> getObservations() {
		return observacoes;
	}

	public void setObservations(List<String> observations) {
		this.observacoes = observations;
	}

	public List<String> getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(List<String> observacoes) {
		this.observacoes = observacoes;
	}

//	public Motorista getMotorista() {
//		return motorista;
//	}
//
//	public void setMotorista(Motorista motorista) {
//		this.motorista = motorista;
//	}

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
		Carro other = (Carro) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Carro [id=" + id + ", modelo=" + modelo + ", ano=" + ano + ", cor=" + cor + ", placa=" + placa
				+ ", observations=" + observacoes + "]";
	}
	
}