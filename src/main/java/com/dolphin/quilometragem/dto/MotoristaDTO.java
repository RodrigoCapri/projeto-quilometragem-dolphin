package com.dolphin.quilometragem.dto;

import java.io.Serializable;

import com.dolphin.quilometragem.domain.Motorista;

public class MotoristaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String nome;
	private String email;
	private String cpf;
	private String carteira;
	private String senha;
	
	private String carro;

	public MotoristaDTO() {
		
	}
	
	public MotoristaDTO(Motorista mot) {
		this.id = mot.getId();
		this.nome = mot.getNome();
		this.email = mot.getEmail();
		this.cpf = mot.getCpf();
		this.carteira = mot.getCarteira();
		this.senha = mot.getSenha();
		this.carro = mot.getCarro() != null ? mot.getCarro().getId() : null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCarro() {
		return carro;
	}

	public void setCarro(String carro) {
		this.carro = carro;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
