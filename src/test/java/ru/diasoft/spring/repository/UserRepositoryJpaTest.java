package ru.diasoft.spring.repository;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.diasoft.spring.entity.User;

@DataJpaTest
@DisplayName("Репозиторий для пользователей")
public class UserRepositoryJpaTest {
    private static final int USERS_COUNT = 2;
    private static final long EXISTING_USER_ID = 1;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("должен возвращать количество пользователей")
    void shouldReturnUsersCount() {
        Long count = userRepository.count();
        assertThat(count).isEqualTo(USERS_COUNT);
    }

    @Test
    @DisplayName("должен возвращать список пользователей")
    void shouldReturnAllUsers() {
        List<User> list = userRepository.findAll();
        assertThat(list.size()).isEqualTo(USERS_COUNT);
    }

    @Test
    @DisplayName("должен возвращать пользователя по идентификатору")
    void shouldReturnUserById() {
        Optional<User> returnedUser = userRepository.findById(EXISTING_USER_ID);
        assertThat(returnedUser.isPresent());
    }

    @Test
    @DisplayName("должен возвращать пользователя по логину")
    void shouldReturnUserByLogin() {
        String login = "user1";
        User user = new User();
        user.setUserName(login);
        user.setPassword("Password");
        user.setIsActive(true);
        user.setRoles("USER");
        userRepository.save(user);
        Optional<User> returnedUser = userRepository.findByUserName(login);
        assertThat(returnedUser.isPresent());
    }

    @Test
    @DisplayName("должен создавать пользователя")
    void shouldCreateUser() {
        String login = "user1";
        String pass = "pass1";
        User user = new User();
        user.setUserName(login);
        user.setPassword(pass);
        user.setIsActive(true);
        user.setRoles("USER");
        userRepository.save(user);
        Optional<User> returnedUser = userRepository.findById(user.getUserId());
        assertThat(returnedUser.get().getUserName()).isEqualTo(login);
        assertThat(returnedUser.get().getPassword()).isEqualTo(pass);
    }

    @Test
    @DisplayName("должен обновлять пользователя")
    void shouldUpdateUser() {
        User user = userRepository.findById(EXISTING_USER_ID).get();
        String login = "user1";
        String pass = "pass1";
        user.setUserName(login);
        user.setPassword(pass);
        userRepository.save(user);
        Optional<User> returnedUser = userRepository.findById(user.getUserId());
        assertThat(returnedUser.get().getUserName()).isEqualTo(login);
        assertThat(returnedUser.get().getPassword()).isEqualTo(pass);
    }

    @Test
    @DisplayName("должен удалять пользователя по идентификатору")
    void shouldDeleteUserById() {
        userRepository.deleteById(EXISTING_USER_ID);

        Optional<User> returnedUser = userRepository.findById(EXISTING_USER_ID);
        assertThat(returnedUser).isNotPresent();
    }


}
