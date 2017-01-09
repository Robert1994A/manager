package ro.inf.ucv.admitere.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

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

	@NotNull
	@NotEmpty
	private String type;

	@Min(value = 1)
	private Long seria;

	@Min(value = 1)
	private Long number;

	@NotNull
	@NotEmpty
	private String emitedBy;

	@Min(value = 1900)
	private int emitedYear;

	@Min(value = 1)
	private int matricolNumber;

	private boolean validatedByDGRIAEorCNRED;

	private Long seriaDGRIAEorCNRED;

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

}
