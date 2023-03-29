package com.company.graphql.service;

import com.company.graphql.dto.request.UserRequest;
import com.company.graphql.exception.UserNotFoundException;
import com.company.graphql.model.User;
import com.company.graphql.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User not found!"));
    }

    public User createUser(UserRequest userRequest) {
        User user  = User.builder()
                .username(userRequest.getUsername())
                .mail(userRequest.getMail())
                .role(userRequest.getRole())
                .build();
        return userRepository.save(user);
    }

    public User updateUser(UserRequest userRequest) {
        User existUser = getUserById(userRequest.getId());
        existUser.setUsername(userRequest.getUsername());
        existUser.setMail(userRequest.getMail());
        existUser.setRole(userRequest.getRole());
        return userRepository.save(existUser);
    }

    public void deleteUser(Long id) {
        User existUser = getUserById(id);
        userRepository.delete(existUser);
    }
}
