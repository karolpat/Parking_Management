package pl.karolpat.service;

import java.util.List;
import java.util.Map;

import pl.karolpat.entity.User;

/**
 * @author karolpat
 *
 */
public interface UserService {

	/**
	 * Gives list of all present User instances.
	 * 
	 * @return List of all present User instances.
	 */
	List<User> getAllUsers();

	/**
	 * Gives User instance found by given id.
	 * 
	 * @param id
	 *            id of User to be returned.
	 * @return User instance of given id or null.
	 */
	User getOneById(long id);

	/**
	 * Gives User instance found by given username.
	 * 
	 * @param username
	 *            username of User to be returned.
	 * @return User instance of given username or null.
	 */
	User getOneByUsername(String username);

	/**
	 * Saves User instances with gives username.
	 * 
	 * @param username
	 *            username of the User to be saved.
	 * @return Saved User instance or String if given username is not available.
	 */
	Object save(String username);

	/**
	 * Changes the vip status of User of given id.
	 * 
	 * @param id
	 *            id of user who has the vip status changed.
	 * @return Edited User or String n case of failure.
	 */
	Object setUserAsVip(long id);

	/**
	 * Gives list of all Users that have vip status.
	 * 
	 * @param vip
	 *            boolean value of vip status. Set to true in the implementation.
	 * @return List of all user with vip status.
	 */
	List<User> findAllWhereVip(boolean vip);

	/**
	 * Starts parking meter.
	 * 
	 * @param number
	 *            registration number of Vehicle. String with number is
	 *            RequestedBody.
	 * @param user
	 *            User who starts the parking meter.
	 * @return User instance who starts the parking.
	 */
	User startParking(String number, User user);

	/**
	 * Finishes User's parking meter. Shows time and cost of stay. Cost is added to
	 * daily income as income.
	 * 
	 * @param user
	 *            User who stops the parking meter.
	 * @return Map containing values of time and cost of stay. Only in PLN so far.
	 */
	Map<String, Double> finishParking(User user);

}
