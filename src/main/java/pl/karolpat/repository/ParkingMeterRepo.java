package pl.karolpat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolpat.entity.ParkingMeter;

/**
 * @author karolpat
 *
 */
public interface ParkingMeterRepo extends JpaRepository<ParkingMeter, Long> {

	/**
	 * Searches for the ParkingMeter instances by User id that is customer. Because
	 * the same user could own many ParkingMeter instances, only the first, the most
	 * current (ordered by start date) record form database is given.
	 * 
	 * @param id id of user whose ParkingMeter is being searched.
	 * @return ParkingMeter instance of given User that is the most up-to-date.
	 */
	ParkingMeter getFirstByUserIdOrderByStart(long id);

}
