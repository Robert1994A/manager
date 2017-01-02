package ro.inf.ucv.admitere.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ro.inf.ucv.admitere.entity.utils.FormOfEducation;

/**
 * Entity implementation class for Entity: PreviousHighSchool
 *
 */
@Entity
@Table
public class PreviousHighSchool implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String absolvedInstitution;

	@OneToOne
	private Country country;

	@OneToOne
	private City city;

	@OneToOne
	private County county;

	private String profil;

	private int lenghtOfStudies;

	private int graduationYear;

	@Enumerated(EnumType.STRING)
	private FormOfEducation formOfEducation;
	
	@OneToOne
	private HighSchoolDiploma highSchoolDiploma;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbsolvedInstitution() {
		return absolvedInstitution;
	}

	public void setAbsolvedInstitution(String absolvedInstitution) {
		this.absolvedInstitution = absolvedInstitution;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

	public int getLenghtOfStudies() {
		return lenghtOfStudies;
	}

	public void setLenghtOfStudies(int lenghtOfStudies) {
		this.lenghtOfStudies = lenghtOfStudies;
	}

	public int getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}

	public FormOfEducation getFormOfEducation() {
		return formOfEducation;
	}

	public void setFormOfEducation(FormOfEducation formOfEducation) {
		this.formOfEducation = formOfEducation;
	}

	public HighSchoolDiploma getHighSchoolDiploma() {
		return highSchoolDiploma;
	}

	public void setHighSchoolDiploma(HighSchoolDiploma highSchoolDiploma) {
		this.highSchoolDiploma = highSchoolDiploma;
	}

}
