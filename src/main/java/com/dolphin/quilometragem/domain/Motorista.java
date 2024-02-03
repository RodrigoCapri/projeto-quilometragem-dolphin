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
	private String senha;
	private String num_acesso;
	
	private Carro carro;
	
	public Motorista() {
	}

	//Quando cria-se um novo motorista
	public Motorista(String id, String nome, String cpf, String carteira, String senha, String num_acesso) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.carteira = carteira;
		this.senha = senha;
		this.num_acesso = num_acesso;
	}
	
	//Quando se faz apenas uma consulta
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNum_acesso() {
		return num_acesso;
	}

	public void setNum_acesso(String num_acesso) {
		this.num_acesso = num_acesso;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
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
		return "Motorista [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", carteira=" + carteira + ", senha=" + senha
				+ ", num_acesso=" + num_acesso + ", carro=" + carro + "]";
	}
	
}
