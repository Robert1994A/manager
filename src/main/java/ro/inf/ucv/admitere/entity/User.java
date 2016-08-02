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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 9093304836112847216L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute(name = "id")
	private Long id;

	@XmlElement(name = "token")
	private String token;

	@XmlElement(name = "username")
	private String username;

	@XmlElement(name = "email")
	private String email;

	@JsonIgnore
	private String password;

	@XmlElement(name = "enabled")
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	@XmlTransient
	private List<Role> roles;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setReset(String token) {
		this.token = token;
	}

}
