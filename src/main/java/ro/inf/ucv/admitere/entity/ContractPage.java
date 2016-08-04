package ro.inf.ucv.admitere.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ContractPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6496531545675385368L;

	@Id
	@GeneratedValue
	private Long id;

	private StringBuilder page;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StringBuilder getPage() {
		return page;
	}

	public void setPage(StringBuilder page) {
		this.page = page;
	}

}
