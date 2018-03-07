package pl.karolpat.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.karolpat.entity.User;
import pl.karolpat.repository.UserRepo;
import pl.karolpat.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepo userRepo;
	
	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getOneById(long id) {
		return userRepo.findOne(id);
	}

	@Override
	public User save(User user) {
		return userRepo.save(user);
	}

	@Override
	public User setUserAsVip(User tmp, long id) {
		User user = userRepo.findOne(id);
		user.setVip(tmp.isVip());
		userRepo.save(user);
		return user;
	}

	@Override
	public List<User> findAllWhereVip(boolean vip) {
		return userRepo.findAllByVip(true);
	}


}
