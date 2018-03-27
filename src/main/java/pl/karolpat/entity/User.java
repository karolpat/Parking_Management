package pl.karolpat.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(columnDefinition = "tinyint(1) default 0")
	private boolean vip = false;

	@Column(columnDefinition = "tinyint(1) default 0")
	private boolean started = false;

	@OneToMany(mappedBy = "owner")
	@JsonManagedReference
	private Set<Vehicle> vehicle;

	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private Set<ParkingMeter> parking;
	
	public User() {}

	public User(String username) {
		this.username = username;
	}

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

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", vip=" + vip + ", started=" + started + ", vehicle="
				+ vehicle + ", parking=" + parking + "]";
	}

}
