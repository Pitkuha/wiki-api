package app.repository;

import app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    int countByUsername(String login);

    User findByUsernameAndPassword(String login, String password);

    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update User u set u.article_count = u.article_count + 1 where u.username = :user")
    void incCount(@Param("user") String user);
}
