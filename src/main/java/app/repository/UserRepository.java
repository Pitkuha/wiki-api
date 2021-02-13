package app.repository;

import app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    int countByUsername(String login);

    User findByUsernameAndPassword(String login, String password);

    User findByUsername(String username);
}
