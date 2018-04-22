/**
 * 
 */
package com.caveofprogramming.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
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

	@Transient
	@Size(min=5, max=15, message="{register.password.size}")
	private String plainPassword;
	
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
}
