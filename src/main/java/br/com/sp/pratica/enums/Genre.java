package br.com.sp.pratica.enums;

public enum Genre {
	MASCULINO(1),
	FEMININO(2),
	SEM_IDENTIFICACAO(3);
	
	private int value;

	private Genre(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public static Genre fromValue(int value) {
		for (Genre genre : values()) {
			if (genre.value == value) {
				return genre;
			}
		}
		throw new IllegalArgumentException("Invalid Genre value: " + value);
	}
}
