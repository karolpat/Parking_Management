package pl.karolpat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolpat.entity.User;

/**
 * @author karolpat
 *
 */
public interface UserRepo extends JpaRepository<User, Long> {

	/**
	 * Gives list of all User instances that have vip status.
	 * 
	 * @param vip
	 *            boolean value of vip status set true in implementation.
	 * @return List of all User instances where vip status is present.
	 */
	List<User> findAllByVip(boolean vip);

	/**
	 * Searches User instance that has given username.
	 * 
	 * @param username
	 *            username of User instance that database is searched by.
	 * @return User instance if such username is present in the database or null in
	 *         oder case.
	 */
	User findOneByUsername(String username);
}
