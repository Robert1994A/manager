package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import javax.persistence.*;

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
	
	private String institutionName;
	
	@OneToOne
	private Country country;
	
	@OneToOne
	private County county;
	
	@OneToOne
	private City city;
	
	private String facultyName;
	
	private String profil;
	
	private String specialization;
	
	private String obtainingTitle;
	
	@Enumerated(EnumType.STRING)
	private FormOfEducation formOfEducation;
	
	@Enumerated(EnumType.STRING)
	private FormOfFinancing formOfFinancing;
	
	private int lengthOfStudies;
	
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
