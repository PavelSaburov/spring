package ru.diasoft.spring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.spring.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
