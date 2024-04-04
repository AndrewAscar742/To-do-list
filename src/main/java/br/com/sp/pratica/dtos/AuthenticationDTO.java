package br.com.sp.pratica.dtos;

import javax.validation.constraints.NotBlank;

public record AuthenticationDTO(
		@NotBlank
		String login,
		@NotBlank
		String password) {

}
