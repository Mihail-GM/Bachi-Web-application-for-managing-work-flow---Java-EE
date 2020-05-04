 package com.example.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "employee")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_Employee")
	private int id;
	
	@Column(name = "manager_id")
	private int managerID;
	
	@Column(name = "position_id")
	private int positionID;
	
	@Column(name = "email")
	@Email(message = "*Моля въведете валиден имейл")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	@Column(name = "password")
	@Length(min = 5, message = "*Паролата трябва да е поне 5 символа")
	@NotEmpty(message = "*Моля въведете парола")
	@Transient
	private String password;
	@Column(name = "name")
	@NotEmpty(message = "*Моля въведете име")
	private String name;
	
	@Column(name = "active")
	private int active;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	
	@Column(name = "Phone")
	private String phoneNumber;
	@Column(name = "Gender")
	private String gender;
	@Column(name = "Birthday")
	private String birthday;
	@Column(name = "created_on")
	private String createOn;
	@Column(name = "updated_on")
	private String updateOn;
	@Column(name = "Adress")
	private String adress;
	@Column(name = "sallary")
	private float sallary;

	

	public User(int int1, String string) {
		this.id = int1;
		this.name = string;
	}

	public User(int id_Employee, String name, String Adress, String Phone, String Gender, float sallary) {
	
	this.id = id_Employee;
		this.name= name;
		
		this.adress= Adress;
		this.phoneNumber = Phone;
		this.gender = Gender ;
		this.sallary = sallary;
	}
	
	public User(int id_Employee, String name,  int managerID, String Adress, String Phone, String Gender, float sallary) {
		
		this.id = id_Employee;
			this.name= name;
			this.managerID = managerID;
			this.adress= Adress;
			this.phoneNumber = Phone;
			this.gender = Gender ;
			this.sallary = sallary;
		}


	
	
	public User(String name, String email,String password, String Adress, String Phone, 
			String Gender, String birthday, float sallary) {
	
		this.email = email;
		this.password = password;
		this.name = name;
		this.phoneNumber = Phone;
		this.gender = Gender;
		this.birthday = birthday;
		this.adress = Adress;
		this.sallary = sallary;
	}



	

	public User(int id, String name, String email, String Adress, String Phone, 
			String Gender, String birthday, float sallary, int managerID, int positionID, int active) {
	
		this.id = id;
		this.email = email;
	
		this.name = name;
		this.phoneNumber = Phone;
		this.gender = Gender;
		this.birthday = birthday;
		this.adress = Adress;
		this.sallary = sallary;
		this.managerID = managerID;
		this.positionID = positionID;
		this.active = positionID;
	}



	public User(int id, int managerID, int positionID, String email, String password, String name, int active,
			Set<Role> roles, String phoneNumber, String gender, String birthday, String createOn, String updateOn,
			 String adress, float sallary) {
		
		this.id = id;
		this.managerID = managerID;
		this.positionID = positionID;
		this.email = email;
		this.password = password;
		this.name = name;
		this.active = active;
		this.roles = roles;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.birthday = birthday;
		this.createOn = createOn;
		this.updateOn = updateOn;

		this.adress = adress;
		this.sallary = sallary;
	}

	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	public int getPositionID() {
		return positionID;
	}

	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public User() {
		
	}





	public float getSallary() {
		return sallary;
	}




	public void setSallary(float sallary) {
		this.sallary = sallary;
	}




	




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}




	public String getPhoneNumber() {
		return phoneNumber;
	}




	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getCreateOn() {
		return createOn;
	}




	public void setCreateOn(String createOn) {
		this.createOn = createOn;
	}




	public String getUpdateOn() {
		return updateOn;
	}




	public void setUpdateOn(String updateOn) {
		this.updateOn = updateOn;
	}












	public String getAdress() {
		return adress;
	}




	public void setAdress(String adress) {
		this.adress = adress;
	}







}
