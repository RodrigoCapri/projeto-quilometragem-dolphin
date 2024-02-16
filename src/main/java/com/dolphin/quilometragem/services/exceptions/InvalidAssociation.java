package com.dolphin.quilometragem.services.exceptions;

public class InvalidAssociation extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidAssociation(String msg) {
		super(msg);
	}
	
}
