package com.dolphin.quilometragem.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "motorista")
public class Motorista implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String nome;
	private String email;
	private String telefone;
	private String cpf;
	private String carteira;
	private String senha = "root";
	
	private Integer num_acesso = 0;
	
	@DBRef
	private Carro carro;
	
	@DBRef(lazy = true)
	private List<Registro> registros = new ArrayList<>();
	
	public Motorista() {
	}

	

	public Motorista(String id, String nome, String email, String telefone, String cpf, String carteira, String senha, Integer num_acesso,
			Carro carro) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.cpf = cpf;
		this.carteira = carteira;
		this.senha = senha != null ? senha : this.senha;
		this.num_acesso = num_acesso != null ? num_acesso : this.num_acesso;
		this.carro = carro;
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

	public Integer getNum_acesso() {
		return num_acesso;
	}

	public void setNum_acesso(Integer num_acesso) {
		this.num_acesso = num_acesso;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
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
		return "Motorista [id=" + id + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", carteira=" + carteira
				+ ", senha=" + senha + ", num_acesso=" + num_acesso + ", carro=" + carro + ", registros=" + registros
				+ "]";
	}
	
}
