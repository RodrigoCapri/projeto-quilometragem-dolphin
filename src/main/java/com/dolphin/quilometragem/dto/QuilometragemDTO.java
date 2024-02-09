package com.dolphin.quilometragem.dto;

import java.io.Serializable;
import java.util.Date;

public class QuilometragemDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String odometro;
	private String destino;
	private Date horario;
	
	public QuilometragemDTO() {
	}

	public QuilometragemDTO(String odometro, String destino, Date horario) {
		this.odometro = odometro;
		this.destino = destino;
		this.horario = horario;
	}

	public String getOdometro() {
		return odometro;
	}

	public void setOdometro(String odometro) {
		this.odometro = odometro;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	@Override
	public String toString() {
		return "QuilometragemDTO [odometro=" + odometro + ", destino=" + destino + ", horario=" + horario + "]";
	}
	
}
