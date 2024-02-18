package com.dolphin.quilometragem.domain.enums;

public enum CarColor {
	
	BRANCO(1),
	AZUL(2),
	AMARELO(3),
	VERDE(4),
	CINZA(5),
	PRETO(6),
	PRATA(7),
	LARANJA(8),
	ROSA(9),
	BORDO(10),
	CARAMELO(11),
	ROXO(12);
	
	private int code;
	
	private CarColor(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public static CarColor valueOf(int code) {
		for(CarColor c : CarColor.values()) {
			if( c.getCode() == code )
				return c;
		}
		throw new IllegalArgumentException("Cor invalida!");
	}
	
}
