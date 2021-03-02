package app.service;

import app.DTO.UserDTO;
import app.domain.Article;
import app.domain.User;
import app.repository.ArticleRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDTOService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UserDTOService(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    public User register(UserDTO userData) {
        User user = new User();
        user.setUsername(userData.getUsername());
        user.setPassword(encoder.encode(userData.getPassword()));
        userRepository.save(user);
        return user;
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByUsername(login) == 0;
    }

    public boolean isLoginThere(String login, String password) {
        String encoded = encoder.encode(password);
        return userRepository.findByUsernameAndPassword(login, encoded) != null;
    }

    public void incrementArtCount(String user) {
        userRepository.incCount(user);
    }

    public boolean checkEditAbility(Article article, String user) {
        return (articleRepository.findByName(article.getName()).getAuthor().getUsername().equals(user))
                || (userRepository.findByUsername(user).getArticle_count() == 3);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDTO(userRepository.findByUsername(username));
    }


}
