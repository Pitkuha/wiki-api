package app.service;

import app.domain.User;
import app.DTO.UserDTO;
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

    @Autowired
    private PasswordEncoder encoder;

    public UserDTOService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public void incrementArtCount(String user){
        userRepository.incCount(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDTO(userRepository.findByUsername(username));
    }


}
