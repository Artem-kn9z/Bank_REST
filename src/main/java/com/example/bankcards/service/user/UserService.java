package com.example.bankcards.service.user;

import com.example.bankcards.entity.user.User;
import com.example.bankcards.repository.user.UserRepository;
import com.example.bankcards.exception.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> findAll() throws EntityNotFoundException{
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            throw new EntityNotFoundException("User not found");
        }
        return users;
    }

    public User findById(int id) throws EntityNotFoundException{
        Optional<User> user = userRepository.findById(id);

        return user.orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User findByEmail(String email) throws EntityNotFoundException{
        Optional<User> user = userRepository.findByEmail(email);

        return user.orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User getUserByFullName(String fullName) throws EntityNotFoundException {
        Optional<User> user = userRepository.findByFullName(fullName);

        return user.orElseThrow(EntityNotFoundException.entityNotFoundException("User '"+fullName+"' not found"));
    }

    @Transactional
    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    public void updateUserById(int id, User user) throws EntityNotFoundException{
        Optional<User> userOptional = userRepository.findById(id);

        userOptional.orElseThrow(() -> new EntityNotFoundException("User not found"));

        userOptional.get().setFullName(user.getFullName());
        userOptional.get().setUsername(user.getUsername());
        userOptional.get().setEmail(user.getEmail());
        userOptional.get().setRole(user.getRole());
        userOptional.get().setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userOptional.get());
    }
}
