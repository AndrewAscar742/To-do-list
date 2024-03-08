package br.com.sp.pratica.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sp.pratica.enums.TaskStatus;

@Entity
@Table(name =  "ToDoLists")
public class ToDoList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private Date due_date;
	private Date date_initial;
	private TaskStatus status;
	
	@ManyToOne()
	private User user;
	
	public ToDoList() {
		this.status = TaskStatus.EM_ANDAMENTO;
	}
	
	public ToDoList(Long id, String title, String description, Date due_date, Date date_initial) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.due_date = due_date;
		this.date_initial = date_initial;
		this.status = TaskStatus.EM_ANDAMENTO;
	}

	public ToDoList(String title, String description, Date due_date, Date date_initial, TaskStatus status,
			User user) {
		super();
		this.title = title;
		this.description = description;
		this.due_date = due_date;
		this.date_initial = date_initial;
		this.status = status;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDoList other = (ToDoList) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "ToDoList [id=" + id + ", title=" + title + ", description=" + description 
				+ ", status=" + status + "]";
	}
	
	
}
