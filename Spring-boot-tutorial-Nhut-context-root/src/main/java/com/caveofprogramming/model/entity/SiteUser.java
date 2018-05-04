/**
 * 
 */
package com.caveofprogramming.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.caveofprogramming.validation.PasswordMatch;
import com.caveofprogramming.validation.UniqueCheck;

/**
 * @author java_dev
 *
 */
@Entity
@Table(name="users")
@PasswordMatch
public class SiteUser {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="email", unique=true, nullable=false)
//	@UniqueCheck(message="This email address already exists")
	@Email(message="{register.email.invalid}")
	@NotBlank(message="{register.email.invalid}")
	private String email;
	
	@Column(name="password", length=60)
	private String password;
	
	@Transient
	private String repeatPassword;
	
	@Column(name="enabled")
	private Boolean enabled = false;
	
	@NotNull
	@Column(name="firstname")
	@Size(min=5, max=25, message="{register.firstname.size}")
	private String firstname;
	
	@NotNull
	@Column(name="surname")
	@Size(min=5, max=25, message="{register.surname.size}")
	private String surname;

	@Transient
	@Size(min=5, max=15, message="{register.password.size}")
	private String plainPassword;
	
	public SiteUser() {
		
	}
	
	public SiteUser(String email, String password, String firstname, String surname) {
		this.email = email;
		this.setPlainPassword(password);
		this.repeatPassword = password;
		this.enabled = true;
		this.firstname = firstname;
		this.surname = surname;
	}

	@Column(name="role", length=20)
	private String role;

	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.password = new BCryptPasswordEncoder().encode(plainPassword);
		this.plainPassword = plainPassword;
	}	

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "SiteUser [id=" + id + ", email=" + email + ", password=" + password + ", repeatPassword="
				+ repeatPassword + ", enabled=" + enabled + ", firstname=" + firstname + ", surname=" + surname
				+ ", plainPassword=" + plainPassword + ", role=" + role + "]";
	}
	
}