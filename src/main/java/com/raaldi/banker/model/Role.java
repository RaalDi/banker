package com.raaldi.banker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({ 
	@NamedQuery(name = "Role.findAll", query = "SELECT c FROM Role c"),
})
public class Role extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "role-seq-gen", sequenceName = "role_seq_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role-seq-gen")
	private Long id;

	@NotNull
	@Column(name = "name", nullable = false, unique = true)
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
	
	@Override
	public String toString() {
		return String.format("Role@%d [id=%d, name=%s]", hashCode(), id, name);
	}

}
