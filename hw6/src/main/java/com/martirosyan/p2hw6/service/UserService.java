package com.martirosyan.p2hw6.service;

import com.martirosyan.p2hw6.model.User;
import com.martirosyan.p2hw6.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository productRepository) {
        this.userRepository = productRepository;
    }

    public Long save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
