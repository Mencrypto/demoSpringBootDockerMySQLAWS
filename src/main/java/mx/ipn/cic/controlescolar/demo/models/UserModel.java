package mx.ipn.cic.controlescolar.demo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class UserModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id //Porque es llave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto -> incremental (el que tenga la base) Identity-> del tipo de la clase
	// Sequence-> nombre de la tabla va consulta id y le incrementa
	@Column(name = "identifier")
	private Integer id;

	@NotEmpty(message = "El nombre no puede ser vacío")
	@Size(min = 3, max = 25, message = "El nombre debe estar entre 3 y 25 caracteres")
	@Column(name = "name")
	private String  name;
	@NotEmpty(message = "No debe ser el apellido vacío")
	@Size(min = 3, max = 10)
	@Column()
	private String lastname;
	@Column(name = "ap_materno")
	private String surname;
	
	@Min(value = 18)
	@Max(value = 70, message = "No puedes tener mas de 70 años")
	//Se puede definir un valor por default que se verá en el formulario usando thymeleaf
	@Column(name = "edad")
	private int age = 28;

	@ManyToOne
	//Es el nombre de la columna en base de datos debe ser exacto
	@JoinColumn(name="rol_id",referencedColumnName ="id")
	private RolModel rol;
	
	public UserModel() {
		super();
	}
	
	public UserModel(String name, String lastname, String surname, int age) {
		super();
		this.age = age;
		this.name = name;
		this.lastname = lastname;
		this.surname = surname;
	}

	public UserModel(int id, String name, String lastname, String surname, int age) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		this.lastname = lastname;
		this.surname = surname;
	}

	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public RolModel getRol() {
		return rol;
	}

	public void setRol(RolModel rol) {
		this.rol = rol;
	}
	
	@Override
	public String toString() {
		return String.format("UserDTO [id=%s, age=%s, name=%s, lastname=%s, surname=%s, rol=%s]", id, age, name, lastname,
				surname,rol);
	}
	
}
