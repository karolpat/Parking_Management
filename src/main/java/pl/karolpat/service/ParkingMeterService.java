package pl.karolpat.service;

import java.util.List;
import java.util.Map;

import pl.karolpat.entity.ParkingMeter;
import pl.karolpat.entity.User;

/**
 * @author karolpat
 *
 */
public interface ParkingMeterService {

	/**
	 * Saves ParkingMeter instance to the database.
	 * 
	 * @param PM
	 *            ParkingMeter instance to be saved to the database.
	 * @return ParkingMeter instance that is being saved.
	 */
	ParkingMeter save(ParkingMeter PM);

	/**
	 * Saves ParkingMeter instance when users finishes its parking meter.
	 * 
	 * @param user
	 *            User instance that is finishing the parking meter.
	 * @return ParkingMeter instance that is being edited and saved.
	 */
	ParkingMeter saveSetEnd(User user);

	/**
	 * Searches for the ParkingMeter instance by User of given id. Only one record
	 * will be returned from database as the most up-to-date instance ordered by
	 * start will be given.
	 * 
	 * @param id
	 *            id of User instance.
	 * @return ParkingMeter instance of User of given id.
	 */
	ParkingMeter findUserParkingMeter(long id);

	/**
	 * Gives list of all ParkingMeter instances.
	 * 
	 * @return List of all ParkingMeter instances.
	 */
	List<ParkingMeter> findAll();

	/**
	 * Checks current cost of the stay at the parking. Map is created to present
	 * data in a more attractive way.
	 * 
	 * @param user
	 *            User who checks the current cost of the stay at the parking.
	 * @return Map that contains String informing about the parameter (currency,
	 *         time) and Double that is value of cost and time.
	 */
	Map<String, Double> checkCost(User user);

	/**
	 * Gives information about number of hours that User of given id spent at the
	 * parking.
	 * 
	 * @param id
	 *            id of User that checks time and cost of stay.
	 * @return value of number of hours spent at the parking.
	 */
	int getCurrentHours(long id);

	/**
	 * Counts total cost of stay at the parking when the User has vip status.
	 * 
	 * @param hours
	 *            number of hours spent at the parking.
	 * @return Total cost of the stay at the parking.
	 */
	double getCostIfVip(int hours);

	/**
	 * Counts total cost of stay at the parking when the User has nom vip status.
	 * 
	 * @param hours
	 *            number of hours spent at the parking.
	 * @return Total cost of the stay at the parking.
	 */
	double getCostUnlessVip(int hours);

}