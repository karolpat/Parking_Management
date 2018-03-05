package pl.karolpat.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ParkingMeter {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private Timestamp start;
	
	private Timestamp end;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="parking_id")
	private User user;
}
