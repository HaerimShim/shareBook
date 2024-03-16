package com.boot.shareBook.service;

import com.boot.shareBook.model.Role;
import com.boot.shareBook.model.User;
import com.boot.shareBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);

        String[] booktypes = user.getBooktype().split(",");
        user.setBooktype1(booktypes[0]);
        user.setBooktype2(booktypes[1]);
        user.setBooktype3(booktypes[2]);

        Role role =  new Role();
        role.setId(1l);
        user.getRoles().add(role);

        return userRepository.save(user);
    }

    public User getUserInfo(String username) {
        return userRepository.findByUsername(username);
    }
}
