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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: Country
 *
 */
@Entity
@Table
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6282422333941131013L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@NotEmpty
	@Size(max = 2, min = 2)
	private String code;

	@NotNull
	@NotEmpty
	private String name;

	@OneToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PreviousHighSchool> previousHighSchools;

	@OneToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PreviousFaculty> previousFaculties;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
