package br.com.sp.pratica.dtos.putDtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PutToDoList {
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date date_initial;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date due_date;
	
	public PutToDoList() {
		// TODO Auto-generated constructor stub
	}
	
	public PutToDoList(String title, String description, Date due_date, Date date_initial) {
		super();
		this.title = title;
		this.description = description;
		this.due_date = due_date;
		this.date_initial = date_initial;
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
	
	
}
