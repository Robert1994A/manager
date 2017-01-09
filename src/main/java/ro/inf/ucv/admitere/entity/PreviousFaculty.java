package ro.inf.ucv.admitere.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import ro.inf.ucv.admitere.entity.utils.FormOfEducation;
import ro.inf.ucv.admitere.entity.utils.FormOfFinancing;

/**
 * Entity implementation class for Entity: PreviousFaculty
 *
 */
@Entity
@Table
public class PreviousFaculty implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@NotEmpty
	private String institutionName;

	@OneToOne(fetch = FetchType.LAZY)
	@NotNull
	@NotEmpty
	private Country country;

	@OneToOne(fetch = FetchType.LAZY)
	@NotNull
	@NotEmpty
	private County county;

	@OneToOne(fetch = FetchType.LAZY)
	@NotNull
	@NotEmpty
	private City city;

	@NotNull
	@NotEmpty
	private String facultyName;

	@NotNull
	@NotEmpty
	private String profil;

	@NotNull
	@NotEmpty
	private String specialization;

	@NotNull
	@NotEmpty
	private String obtainingTitle;

	@Enumerated(EnumType.STRING)
	@NotNull
	@NotEmpty
	private FormOfEducation formOfEducation;

	@Enumerated(EnumType.STRING)
	@NotNull
	@NotEmpty
	private FormOfFinancing formOfFinancing;

	@Min(value = 1)
	private int lengthOfStudies;

	@Min(value = 1900)
	private int graduationYear;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getObtainingTitle() {
		return obtainingTitle;
	}

	public void setObtainingTitle(String obtainingTitle) {
		this.obtainingTitle = obtainingTitle;
	}

	public FormOfEducation getFormOfEducation() {
		return formOfEducation;
	}

	public void setFormOfEducation(FormOfEducation formOfEducation) {
		this.formOfEducation = formOfEducation;
	}

	public FormOfFinancing getFormOfFinancing() {
		return formOfFinancing;
	}

	public void setFormOfFinancing(FormOfFinancing formOfFinancing) {
		this.formOfFinancing = formOfFinancing;
	}

	public int getLengthOfStudies() {
		return lengthOfStudies;
	}

	public void setLengthOfStudies(int lengthOfStudies) {
		this.lengthOfStudies = lengthOfStudies;
	}

	public int getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}

}
