package com.example.bolgsystem.Service;

import com.example.bolgsystem.Api.ApiException;
import com.example.bolgsystem.Model.User;
import com.example.bolgsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AuthRepository authRepository;

    public void deleteUser(String userName) {
        User user = authRepository.findUserByUsername(userName);
        if(user == null){
            throw new ApiException(" user not found ");
        }
        authRepository.delete(user);
    }

    public List<User> getAllUsers(){
        return authRepository.findAll();
    }

}
