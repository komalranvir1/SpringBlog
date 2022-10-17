package komal.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import komal.example.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String userName);

}
