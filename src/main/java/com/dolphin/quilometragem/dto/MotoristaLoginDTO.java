package com.dolphin.quilometragem.dto;

import java.io.Serializable;

public class MotoristaLoginDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String senha;
	private Integer num_acesso;
	
	public MotoristaLoginDTO(String cpf, String senha, Integer num_acesso) {
		this.cpf = cpf;
		this.senha = senha;
		this.num_acesso = num_acesso;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

}
