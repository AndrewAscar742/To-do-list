package br.com.sp.pratica.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.sp.pratica.enums.TaskStatus;

@JsonInclude(value = Include.NON_NULL)
public class ToDoListDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@JsonProperty(access = Access.READ_WRITE)
	@NotBlank
	private String title;
	
	@JsonProperty(access = Access.READ_WRITE)
	@NotBlank
	private String description;
	
	@JsonProperty(access = Access.READ_ONLY)
	private UserDTO user;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty(access = Access.READ_WRITE)
	@NotNull
	private Date due_date;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty(access = Access.READ_WRITE)
	@NotNull
	private Date date_initial;
	
	@JsonProperty(access = Access.READ_ONLY)
	private TaskStatus status;
	
	public ToDoListDTO() {
		// TODO Auto-generated constructor stub
	}

	public ToDoListDTO(Long id, String title, String description, Date due_date, Date date_initial, TaskStatus status,
			UserDTO userDTO) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.due_date = due_date;
		this.date_initial = date_initial;
		this.status = status;
		this.user = userDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public Date getDate_initial() {
		return date_initial;
	}

	public void setDate_initial(Date date_initial) {
		this.date_initial = date_initial;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUserDTO(UserDTO user) {
		this.user = user;
	}
	
	
	
}
