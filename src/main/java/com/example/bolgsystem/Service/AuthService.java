package com.example.bolgsystem.Service;

import com.example.bolgsystem.Model.User;
import com.example.bolgsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;


    // this is added .
    public void register(User user){
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("USER");
        authRepository.save(user);
    }

    public void updated(Integer id, User user){
        User user1 = authRepository.findUserById(id);
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());

        user1.setUsername(user.getUsername());
        user1.setPassword(hash);
        authRepository.save(user1);
    }

    public void deleted(String userName){
        User user1 = authRepository.findUserByUsername(userName);
       authRepository.delete(user1);
    }

    public User getInfo(String userName){
        User user1 = authRepository.findUserByUsername(userName);
        return user1;
    }


}
