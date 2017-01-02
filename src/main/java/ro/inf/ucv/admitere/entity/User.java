package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ro.inf.ucv.admitere.annotation.UniqueEmail;
import ro.inf.ucv.admitere.annotation.UniqueUsername;

@Entity
@Table
public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 9093304836112847216L;

	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column
	@Id
	private String id;

	@UniqueUsername(message = "This username already exist! Please try another one!")
	@NotNull
	@NotEmpty
	private String username;

	@UniqueEmail(message = "This email already exist! Please try another one!")
	@Email
	@NotNull
	@NotEmpty
	private String email;

	@JsonIgnore
	@NotNull
	@NotEmpty
	private String password;

	private String registerToken;

	private String recoverPaswordToken;

	private Date creationDate;

	private Date expiredDate;

	private boolean expired;

	private boolean enabled;

	@OneToOne
	private Contract contract;

	@OneToOne
	private UserPersonalData userPersonalData;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private List<Role> roles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public UserPersonalData getUserPersonalData() {
		return userPersonalData;
	}

	public void setUserPersonalData(UserPersonalData userPersonalData) {
		this.userPersonalData = userPersonalData;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

}
