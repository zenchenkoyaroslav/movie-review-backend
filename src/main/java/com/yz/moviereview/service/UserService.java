package com.yz.moviereview.service;

import com.yz.moviereview.entities.User;
import com.yz.moviereview.exceptions.ValidationException;
import com.yz.moviereview.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private MessageDigest messageDigest;

    @PostConstruct
    private void init() throws NoSuchAlgorithmException {
        messageDigest = MessageDigest.getInstance("SHA-256");
    }

    private String encodePassword(String password){
        byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public User login(String username, String password){
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new ValidationException("User doesnt exists");
        }
        String encodedPassword = encodePassword(password);
        if (!encodedPassword.equals(user.getPassword())){
            throw new ValidationException("Bad password");
        }
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        user = userRepository.save(user);
        return user;
    }

    public void logout(User user){
        User userDB = userRepository.findById(user.getId()).orElse(null);
        if (userDB != null){
            user.setToken(null);
            userRepository.save(user);
        }
    }

    public User getUserByToken(String token){
        return userRepository.findByToken(token);
    }


}
