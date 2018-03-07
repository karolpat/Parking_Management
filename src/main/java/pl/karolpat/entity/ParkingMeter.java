package pl.karolpat.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ParkingMeter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private Timestamp start;

	private Timestamp end;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	@OneToOne
	private Vehicle vehicle;

	
	public ParkingMeter() {}

	public ParkingMeter(Timestamp start, User user, Vehicle vehicle) {
		this.start = start;
		this.user = user;
		this.vehicle = vehicle;
	}

	// GETTERS AND SETTERS

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ParkingMeter [id=" + id + ", start=" + start + ", end=" + end + ", user=" + user + "]";
	}

}
