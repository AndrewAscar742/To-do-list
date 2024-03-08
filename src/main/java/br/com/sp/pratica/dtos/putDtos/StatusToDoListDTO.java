package br.com.sp.pratica.dtos.putDtos;

import javax.validation.constraints.NotNull;

import br.com.sp.pratica.enums.TaskStatus;

public record StatusToDoListDTO(
		@NotNull
		TaskStatus status) {

}
