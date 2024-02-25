package br.com.sp.pratica.enums;

public enum UserRole {
	ADMIN(1, "Admin"),
	USER(2, "User");
	
	private Integer id;
	private String name;
	
	private UserRole(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
