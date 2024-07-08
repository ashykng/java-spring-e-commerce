package com.example.online_shop.services;

import com.example.online_shop.dto.UserDto;
import com.example.online_shop.models.User;
import com.example.online_shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductService productService;

    public void create(UserDto userDto) throws Exception {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new Exception("Email already in use");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());

        // Set default role if not provided
        if (userDto.getRole() == null) {
            user.setRole("USER");
        } else {
            user.setRole(userDto.getRole());
        }

        userRepository.save(user);
    }

    //find by ..
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
    public User findById(int id) {
        return userRepository.findById(id);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //get
    public List<User> getSellers() {
        return userRepository.findByRole("SELLER");
    }
    public long getUsersCount() {
        return userRepository.count();
    }
    public int getUserId(String name) {
        User user = findByName(name);
        return user != null ? user.getId() : -1;
    }
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //delete
    public void deleteUser(User entity) {
        userRepository.delete(entity);
    }
    public void deleteUserAndProducts(User user) {
        userRepository.delete(user);
    }
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    //update
    public void updateAddress(int userId, String newAddress) {
        User user = userRepository.findById(userId);
        user.setAddress(newAddress);
        userRepository.save(user);
    }
    public void updateContactInfo(int userId, Long newPhone, String newAddress) {
        User user = userRepository.findById(userId);
        boolean updated = false;

        if (newPhone != null && !newPhone.equals(user.getNumber())) {
            user.setNumber(newPhone);
            updated = true;
        }

        if (newAddress != null && !newAddress.equals(user.getAddress())) {
            user.setAddress(newAddress);
            updated = true;
        }

        if (updated) {
            userRepository.save(user);
        }
    }

    //other
    public boolean verifyPassword(String rawPassword, String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }
    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;
    }

}

