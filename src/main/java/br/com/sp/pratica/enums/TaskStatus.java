package br.com.sp.pratica.enums;

public enum TaskStatus {
	CONCLUIDO(1),
	EM_ANDAMENTO(2),
	CANCELADO(3);
	
	private int value;

	private TaskStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public static TaskStatus fromValue(int value) {
		for (TaskStatus taskStatus : values()) {
			if (taskStatus.getValue() == value)
				return taskStatus;
		}
		
		throw new IllegalArgumentException("Invalid TaskStaus value: " + value);
	}
}
