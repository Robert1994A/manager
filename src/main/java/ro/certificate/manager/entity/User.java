package ro.certificate.manager.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import ro.certificate.manager.annotation.UniqueEmail;
import ro.certificate.manager.annotation.UniqueUsername;

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

	@UniqueUsername(message = "Acest nume de utilizator deja exista!")
	@NotNull(message = "Va rugam completati campul nume utilizator!")
	@NotEmpty(message = "Va rugam completati campul nume utilizator!")
	private String username;

	@UniqueEmail(message = "Acest email deja exista. Va rugam incercati altul.")
	@Email(message = "Introducesti un email valid.")
	@NotNull(message = "Introducesti un email valid.")
	@NotEmpty(message = "Introducesti un email valid.")
	private String email;

	@NotNull(message = "Introducesti o parola valida.")
	@NotEmpty(message = "Introducesti o parola valida.")
	@Size(min = 6, max = 256, message = "Parola trebuie sa contina intre 6 si 256 caractere.")
	private String password;

	private String registerToken;

	private String recoverPaswordToken;

	private Date creationDate;

	private Date expiredDate;

	private boolean expired;

	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.TRUE)
	@JoinTable
	private List<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<Keystore> keyStores;

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

	public List<Keystore> getKeyStores() {
		return keyStores;
	}

	public void setKeyStores(List<Keystore> keyStores) {
		this.keyStores = keyStores;
	}

}
