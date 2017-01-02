package ro.inf.ucv.admitere.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table
@Entity
public class Department {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String description;

	private String email;

	private String phoneNumber;

	@OneToMany
	private List<AdmissionSession> admissionSessions;
	
	@OneToMany
	private Set<Specialization> specializations;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<AdmissionSession> getAdmissionSessions() {
		return admissionSessions;
	}

	public void setAdmissionSessions(List<AdmissionSession> admissionSessions) {
		this.admissionSessions = admissionSessions;
	}

	public Set<Specialization> getSpecializations() {
		return specializations;
	}

	public void setSpecializations(Set<Specialization> specializations) {
		this.specializations = specializations;
	}
}
