package pl.karolpat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolpat.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	
	List<User> findAllByVip(boolean vip);
}
