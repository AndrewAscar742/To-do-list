package br.com.sp.pratica.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.sp.pratica.enums.Genre;

@JsonInclude(Include.NON_NULL)
public class UserDTO {

	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@JsonProperty(access = Access.READ_WRITE)
	@NotBlank
	private String name;

	@JsonProperty(access = Access.READ_WRITE)
	@NotNull
	@NotEmpty
	private Integer age;

	@JsonProperty(access = Access.READ_WRITE)
	@NotNull
	@NotEmpty
	private Genre genre;

	@JsonProperty(access = Access.READ_ONLY)
	@JsonFormat(pattern = "dd/MM//yyyy")
	private Date date_initial;
	
	@JsonProperty(access = Access.READ_ONLY)
	@JsonFormat(pattern = "dd/MM//yyyy")
	private Date data_edition;

	@JsonProperty(access = Access.READ_WRITE)
	@NotBlank
	private String email;

	@JsonProperty(access = Access.READ_WRITE)
	@NotBlank
	private String password;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(Long id, String name, Integer age, Genre genre, Date date_initial, Date data_edition, String email,
			String password) {
		super();
		this.id = id;
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

}
