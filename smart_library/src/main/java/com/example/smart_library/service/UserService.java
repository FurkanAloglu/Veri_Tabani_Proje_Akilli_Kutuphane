package com.example.smart_library.service;

import org.springframework.stereotype.Service;
import com.example.smart_library.model.User;
import com.example.smart_library.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public Optional<User> findById(UUID id){
        return userRepository.findById(id);
    }

    public void deleteById(UUID id){
        userRepository.deleteById(id);
    }
}
