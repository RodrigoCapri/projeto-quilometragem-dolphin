package com.dolphin.quilometragem.domain;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "motorista")
public class Motorista implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String nome;
	private String cpf;
	private String carteira;
	
	public Motorista() {
	}

	public Motorista(String id, String nome, String cpf, String carteira) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.carteira = carteira;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
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
		Motorista other = (Motorista) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Motorista [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", carteira=" + carteira + "]";
	}
	
}
