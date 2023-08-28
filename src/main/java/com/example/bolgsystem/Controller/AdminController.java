package com.example.bolgsystem.Controller;

import com.example.bolgsystem.Api.ApiResponse;
import com.example.bolgsystem.Model.User;
import com.example.bolgsystem.Repository.AuthRepository;
import com.example.bolgsystem.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;


    @GetMapping("/")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllUsers());
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal User user){
        adminService.deleteUser(user.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("delete"));
    }

}
