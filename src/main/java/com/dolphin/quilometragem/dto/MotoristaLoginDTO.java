package com.dolphin.quilometragem.dto;

import java.io.Serializable;

public class MotoristaLoginDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String senha;
	
	public MotoristaLoginDTO(String cpf, String senha) {
		this.cpf = cpf;
		this.senha = senha;
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

	@Override
	public String toString() {
		return "MotoristaLoginDTO [cpf=" + cpf + ", senha=" + senha + "]";
	}

}
