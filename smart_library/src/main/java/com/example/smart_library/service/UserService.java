package com.example.smart_library.service;

import org.springframework.stereotype.Service;
import com.example.smart_library.model.User;
import com.example.smart_library.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import com.example.smart_library.dto.user.UserRequest;
import com.example.smart_library.dto.user.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> findAll(){
        return userRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public UserResponse save(UserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    public Optional<UserResponse> findById(UUID id){
        return userRepository.findById(id).map(this::toResponse);
    }

    public void deleteById(UUID id){
        userRepository.deleteById(id);
    }

    private UserResponse toResponse(User u){
        return UserResponse.builder()
                .id(u.getUserID())
                .name(u.getName())
                .surname(u.getSurname())
                .email(u.getEmail())
                .build();
    }
}
