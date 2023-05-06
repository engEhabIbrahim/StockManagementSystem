package eg.edu.bsu.fcai.stockmanagementsystem.service.entities;

import eg.edu.bsu.fcai.stockmanagementsystem.model.entities.User;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.entities.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static eg.edu.bsu.fcai.stockmanagementsystem.assets.ExceptionsMessagesRepository.*;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    public User findById(String id) {
        return repository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(ID_NOT_FOUND, id)));
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(EMAIL_NOT_FOUND, email)));
    }

    public User add(User user) {
        return repository.save(user);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public long count() {
        return repository.count();
    }

    public List<User> findAllDisabledUsers() {
        return repository.findAllByIsEnabled(false);
    }

    public List<User> findAllEnabledUsers() {
        return repository.findAllByIsEnabled(true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return this.findByEmail(username);
    }
}
