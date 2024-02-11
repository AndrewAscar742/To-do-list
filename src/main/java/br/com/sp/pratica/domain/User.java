package br.com.sp.pratica.domain;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.sp.pratica.enums.Genre;

@Entity
@Table(name = "users")

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer age;
	private Genre genre;
	private Date date_initial;
	private Date data_edition;
	private String email;
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ToDoList> toDoList;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String name, Integer age, Genre genre, Date date_initial, Date data_edition, String email,
			String password) {
		super();
		this.name = name;
		this.age = age;
		this.genre = genre;
		this.date_initial = date_initial;
		this.data_edition = data_edition;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Date getDate_initial() {
		return date_initial;
	}

	public void setDate_initial(Date date_initial) {
		this.date_initial = date_initial;
	}

	public Date getData_edition() {
		return data_edition;
	}

	public void setData_edition(Date data_edition) {
		this.data_edition = data_edition;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ToDoList> getToDoList() {
		return toDoList;
	}

	public void setToDoList(List<ToDoList> toDoList) {
		this.toDoList = toDoList;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
}
