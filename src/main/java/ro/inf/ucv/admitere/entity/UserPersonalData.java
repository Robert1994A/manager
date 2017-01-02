package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ro.inf.ucv.admitere.annotation.UniqueCnp;
import ro.inf.ucv.admitere.annotation.UniquePhoneNumber;

@Entity
@Table
public class UserPersonalData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3285817090597418213L;

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message = "familyNameBirtCertificate must not be blank!")
	@NotNull
	@Min(value = 2, message = "Min value length is 2")
	private String familyNameBirtCertificate;

	@NotEmpty
	@NotNull
	private String familyNameActual;

	@NotEmpty
	@NotNull
	private String forename;

	@NotEmpty
	@NotNull
	private String initialMotherForename;

	@NotEmpty
	@NotNull
	private String initialFatherForename;

	@UniqueCnp(message = "This CNP already exist!")
	@NotNull
	@NotEmpty
	@Size(max = 13, min = 13)
	private String cnp;

	private int birthDay;

	private int birthMonth;

	private int birthYear;

	private String sexType;

	private String maritalStatus;

	private String socialStatus;

	private String citizenship;

	private String ethnicity;

	private String religion;

	@UniquePhoneNumber(message = "This phoneNumber already exist!")
	private String phoneNumber;

	@OneToOne
	@JsonIgnore
	private Address address;

	@OneToMany
	@JsonIgnore
	private List<IdentityCard> identityCard;

	@OneToMany
	@JsonIgnore
	private Set<PreviousHighSchool> prevoiusHighschool;

	@OneToMany
	@JsonIgnore
	private Set<PreviousFaculty> previousFaculty;

	@OneToOne
	@JsonIgnore
	private LegalParentFather legalParentFather;

	@OneToOne
	@JsonIgnore
	private LegalParentMother legalParentMother;

	public Set<PreviousHighSchool> getPrevoiusHighschool() {
		return prevoiusHighschool;
	}

	public void setPrevoiusHighschool(Set<PreviousHighSchool> prevoiusHighschool) {
		this.prevoiusHighschool = prevoiusHighschool;
	}

	public Set<PreviousFaculty> getPreviousFaculty() {
		return previousFaculty;
	}

	public void setPreviousFaculty(Set<PreviousFaculty> previousFaculty) {
		this.previousFaculty = previousFaculty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFamilyNameBirtCertificate() {
		return familyNameBirtCertificate;
	}

	public void setFamilyNameBirtCertificate(String familyNameBirtCertificate) {
		this.familyNameBirtCertificate = familyNameBirtCertificate;
	}

	public String getFamilyNameActual() {
		return familyNameActual;
	}

	public void setFamilyNameActual(String familyNameActual) {
		this.familyNameActual = familyNameActual;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getInitialMotherForename() {
		return initialMotherForename;
	}

	public void setInitialMotherForename(String initialMotherForename) {
		this.initialMotherForename = initialMotherForename;
	}

	public String getInitialFatherForename() {
		return initialFatherForename;
	}

	public void setInitialFatherForename(String initialFatherForename) {
		this.initialFatherForename = initialFatherForename;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getSexType() {
		return sexType;
	}

	public void setSexType(String sexType) {
		this.sexType = sexType;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getSocialStatus() {
		return socialStatus;
	}

	public void setSocialStatus(String socialStatus) {
		this.socialStatus = socialStatus;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<IdentityCard> getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(List<IdentityCard> identityCard) {
		this.identityCard = identityCard;
	}

}
