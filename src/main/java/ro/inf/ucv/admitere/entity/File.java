package ro.inf.ucv.admitere.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: File
 *
 */
@Entity
@Table
public class File implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

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
