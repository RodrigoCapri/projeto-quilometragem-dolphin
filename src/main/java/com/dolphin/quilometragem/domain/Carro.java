package com.dolphin.quilometragem.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dolphin.quilometragem.dto.MotoristaDTO;
import com.dolphin.quilometragem.dto.QuilometragemDTO;

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
	
	private List<QuilometragemDTO> quilometragem = new ArrayList<>();
	
	private MotoristaDTO motorista;
	
	public Carro() {
	}

	public Carro(String id, String modelo, String ano, String cor, String placa, MotoristaDTO motorista, List<String> observacoes) {
		this.id = id;
		this.modelo = modelo;
		this.ano = ano;
		this.cor = cor;
		this.placa = placa;
		this.observacoes = observacoes;
		this.motorista = motorista;
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

	public List<String> getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(List<String> observacoes) {
		this.observacoes = observacoes;
	}

	public MotoristaDTO getMotorista() {
		return motorista;
	}

	public void setMotorista(MotoristaDTO motorista) {
		this.motorista = motorista;
	}

	public List<QuilometragemDTO> getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(List<QuilometragemDTO> quilometragem) {
		this.quilometragem = quilometragem;
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
		Carro other = (Carro) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Carro [id=" + id + ", modelo=" + modelo + ", ano=" + ano + ", cor=" + cor + ", placa=" + placa
				+ ", observacoes=" + observacoes + ", quilometragem=" + quilometragem + ", motorista=" + motorista
				+ "]";
	}
	
}
