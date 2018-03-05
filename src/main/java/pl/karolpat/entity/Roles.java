package pl.karolpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private long id;
	
	private String role;
	
	private String sub_name;
	
	//GETTERS AND SETTERS

	public long getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public String getSub_name() {
		return sub_name;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", role=" + role + ", sub_name=" + sub_name + "]";
	}
	
}
