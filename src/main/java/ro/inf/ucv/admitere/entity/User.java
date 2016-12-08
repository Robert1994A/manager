package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ro.inf.ucv.admitere.annotation.UniqueUsername;

@Entity
@Table
public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 9093304836112847216L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@UniqueUsername(message = "This username already exist!")
	@NotNull
	@NotEmpty
	private String username;

	@Email
	@NotNull
	@NotEmpty
	private String email;

	@JsonIgnore
	@NotNull
	@NotEmpty
	private String password;

	private boolean enabled;

	@NotNull
	@NotEmpty
	private String registerToken;

	@NotNull
	@NotEmpty
	private String recoverPaswordToken;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	@NotNull
	private List<Role> roles;

	@OneToOne
	private Contract contract;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRegisterToken() {
		return registerToken;
	}

	public void setRegisterToken(String registerToken) {
		this.registerToken = registerToken;
	}

	public String getRecoverPaswordToken() {
		return recoverPaswordToken;
	}

	public void setRecoverPaswordToken(String recoverPaswordToken) {
		this.recoverPaswordToken = recoverPaswordToken;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

}
