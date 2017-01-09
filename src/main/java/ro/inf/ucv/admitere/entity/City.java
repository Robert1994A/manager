package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: City
 *
 */
@Entity
@Table
public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6141506711814012943L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@NotEmpty
	private String name;

	@OneToMany(fetch = FetchType.LAZY)
	private List<PreviousHighSchool> previousHighSchools;

	@OneToMany(fetch = FetchType.LAZY)
	private List<PreviousFaculty> previousFaculties;

	public List<PreviousHighSchool> getPreviousHighSchools() {
		return previousHighSchools;
	}

	public void setPreviousHighSchools(List<PreviousHighSchool> previousHighSchools) {
		this.previousHighSchools = previousHighSchools;
	}

	public List<PreviousFaculty> getPreviousFaculties() {
		return previousFaculties;
	}

	public void setPreviousFaculties(List<PreviousFaculty> previousFaculties) {
		this.previousFaculties = previousFaculties;
	}

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

}
