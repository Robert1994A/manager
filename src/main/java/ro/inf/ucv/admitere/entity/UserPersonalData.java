package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ro.inf.ucv.admitere.annotation.UniqueCnp;
import ro.inf.ucv.admitere.annotation.UniquePhoneNumber;
import ro.inf.ucv.admitere.entity.utils.Gender;
import ro.inf.ucv.admitere.entity.utils.MaritalStatus;
import ro.inf.ucv.admitere.entity.utils.SocialStatus;

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

	@NotBlank(message = "familyNameBirthCertificate must not be blank!")
	@NotNull
	private String familyNameBirtCertificate;

	@NotEmpty
	@NotNull
	private String familyNameActual;

	@NotEmpty
	@NotNull
	private String forename;

	@NotEmpty
	@NotNull
	@Size(min = 1, max = 1)
	private String initialMotherForename;

	@NotEmpty
	@NotNull
	@Size(min = 1, max = 1)
	private String initialFatherForename;

	@UniqueCnp(message = "This CNP already exist!")
	@NotNull
	@NotEmpty
	@Size(max = 13, min = 13)
	private String cnp;

	@Min(value = 1)
	@Max(value = 31)
	private int birthDay;

	@Min(value = 1)
	@Max(value = 12)
	private int birthMonth;

	@Min(value = 1900)
	@Max(value = 2100)
	private int birthYear;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;

	@Enumerated(EnumType.STRING)
	private SocialStatus socialStatus;

	@NotNull
	@NotEmpty
	private String citizenship;

	@NotNull
	@NotEmpty
	private String ethnicity;

	@NotNull
	@NotEmpty
	private String religion;

	@UniquePhoneNumber(message = "This phoneNumber already exist!")
	private String phoneNumber;

	@OneToOne
	@JsonIgnore
	private Address address;

	@OneToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	private List<IdentityCard> identityCard;

	@OneToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<PreviousHighSchool> previousHighschool;

	@OneToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<PreviousFaculty> previousFaculty;

	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private LegalParentFather legalParentFather;

	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private LegalParentMother legalParentMother;

	@OneToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PersonalFile> personalFiles;

	@OneToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	private List<File> files;

	@OneToOne
	private User user;

	public Set<PreviousHighSchool> getPreviousHighschool() {
		return previousHighschool;
	}

	public void setPreviousHighschool(Set<PreviousHighSchool> previousHighschool) {
		this.previousHighschool = previousHighschool;
	}

	public LegalParentFather getLegalParentFather() {
		return legalParentFather;
	}

	public void setLegalParentFather(LegalParentFather legalParentFather) {
		this.legalParentFather = legalParentFather;
	}

	public LegalParentMother getLegalParentMother() {
		return legalParentMother;
	}

	public void setLegalParentMother(LegalParentMother legalParentMother) {
		this.legalParentMother = legalParentMother;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public SocialStatus getSocialStatus() {
		return socialStatus;
	}

	public void setSocialStatus(SocialStatus socialStatus) {
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

	public List<PersonalFile> getPersonalFiles() {
		return personalFiles;
	}

	public void setPersonalFiles(List<PersonalFile> personalFiles) {
		this.personalFiles = personalFiles;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public String getFamilyNameBirtCertificate() {
		return familyNameBirtCertificate;
	}

	public void setFamilyNameBirtCertificate(String familyNameBirtCertificate) {
		this.familyNameBirtCertificate = familyNameBirtCertificate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
