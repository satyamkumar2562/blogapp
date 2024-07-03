package com.blogapp2.controller;

import com.blogapp2.entity.User;
import com.blogapp2.payload.LoginDto;
import com.blogapp2.payload.SignUp;
import com.blogapp2.repository.UserRepository;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/sign-up")
    public ResponseEntity<?>createUser(@RequestBody SignUp signUp){
        if(userRepository.existsByEmail(signUp.getEmail())){
            return new ResponseEntity<>("email already registered", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        if(userRepository.existsByUsername(signUp.getUsername())){
            return new ResponseEntity<>("username already registered",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user=new User();
        user.setName(signUp.getName());
        user.setUsername(signUp.getUsername());
        user.setEmail(signUp.getEmail());
        user.setPassword(passwordEncoder.encode(signUp.getPassword()) );
         userRepository.save(user);
         return new ResponseEntity<>("user registered",HttpStatus.CREATED);

    }
@PostMapping("/sign-in")
    public ResponseEntity<String>signIn(@RequestBody LoginDto loginDto)
{
    UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
    Authentication authenticate =
    authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authenticate);
    return new ResponseEntity<>("sign in successful",HttpStatus.OK);

}

}
