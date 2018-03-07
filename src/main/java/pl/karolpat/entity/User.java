package pl.karolpat.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	@NotBlank
	private String password;

	@Column(unique = true, nullable = false)
	@Email
	private String email;

	private boolean vip = false;
	private boolean started = false;

	@OneToMany(mappedBy = "owner")
	private Set<Vehicle> vehicle;

	@OneToOne
	private Roles role;

	@OneToMany(mappedBy = "user")
	private Set<ParkingMeter> parking;

	// GETERS AND SETTERS
	

	public long getId() {
		return id;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public Set<Vehicle> getVehicle() {
		return vehicle;
	}

	public void setVehicle(Set<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public Set<ParkingMeter> getParking() {
		return parking;
	}

	public void setParking(Set<ParkingMeter> parking) {
		this.parking = parking;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", vip="
				+ vip + ", vehicle=" + vehicle + ", role=" + role + ", parking=" + parking + "]";
	}

}
