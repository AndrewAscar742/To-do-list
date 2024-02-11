package br.com.sp.pratica.dtos.postDtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class PostToDoList {
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull
	private Long user_id;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotBlank
	private String title;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotBlank
	private String description;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull
	private Date due_date;
	
	
	public PostToDoList() {
		// TODO Auto-generated constructor stub
	}

	public PostToDoList(Long user_id,String title,String description,
			Date due_date,Date date_initial) {
		super();
		this.user_id = user_id;
		this.title = title;
		this.description = description;
		this.due_date = due_date;

	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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
	
	
}
