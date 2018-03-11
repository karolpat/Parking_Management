package pl.karolpat.service;

import java.util.List;
import java.util.Map;

import pl.karolpat.entity.User;

public interface UserService {

	List<User> getAllUsers();

	User getOneById(long id);
	
	User getOneByUsername(String username);

	Object save(String username);

	User setUserAsVip(User tmp, long id);

	List<User> findAllWhereVip(boolean vip);

	User startParking(String number, User user);

	User checkParking(User user);

	Map<String, Double> finishParking(User user);

}
