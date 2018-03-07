package pl.karolpat.service;

import java.util.List;

import pl.karolpat.entity.User;

public interface UserService {

	List<User> getAllUsers();
	User getOneById(long id);
	User save(User user);
	
	User setUserAsVip(User tmp, long id);
	List<User> findAllWhereVip(boolean vip);
	
	
}
