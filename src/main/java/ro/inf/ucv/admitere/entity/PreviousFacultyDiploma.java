package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: PreviousFacultyDiploma
 *
 */
@Entity
@Table
public class PreviousFacultyDiploma implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String type;

	private Long seria;

	private Long number;

	private String emitedBy;

	private int emitedYear;
	
	private int matricolNumber;
	
	private boolean validatedByDGRIAEorCNRED;
	
	private Long seriaDGRIAEorCNRED;
	
	@OneToMany
	private Set<File> files;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSeria() {
		return seria;
	}

	public void setSeria(Long seria) {
		this.seria = seria;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getEmitedBy() {
		return emitedBy;
	}

	public void setEmitedBy(String emitedBy) {
		this.emitedBy = emitedBy;
	}

	public int getEmitedYear() {
		return emitedYear;
	}

	public void setEmitedYear(int emitedYear) {
		this.emitedYear = emitedYear;
	}

	public int getMatricolNumber() {
		return matricolNumber;
	}

	public void setMatricolNumber(int matricolNumber) {
		this.matricolNumber = matricolNumber;
	}

	public boolean isValidatedByDGRIAEorCNRED() {
		return validatedByDGRIAEorCNRED;
	}

	public void setValidatedByDGRIAEorCNRED(boolean validatedByDGRIAEorCNRED) {
		this.validatedByDGRIAEorCNRED = validatedByDGRIAEorCNRED;
	}

	public Long getSeriaDGRIAEorCNRED() {
		return seriaDGRIAEorCNRED;
	}

	public void setSeriaDGRIAEorCNRED(Long seriaDGRIAEorCNRED) {
		this.seriaDGRIAEorCNRED = seriaDGRIAEorCNRED;
	}

	public Set<File> getFiles() {
		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}
	
	

}
