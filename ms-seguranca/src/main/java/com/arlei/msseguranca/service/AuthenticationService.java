package com.arlei.msseguranca.service;

import com.arlei.msseguranca.entity.User;
import com.arlei.msseguranca.repository.UserRepository;
import com.arlei.msseguranca.request.UserAuthRequest;
import com.arlei.msseguranca.request.UserRequest;
import com.arlei.msseguranca.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    // Injetando o bean da SecutiryConfigurantions que vai bucar os dados da autentucação que implementamos na User pelo USERDETAILS
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity login(UserAuthRequest data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        // Metodo que implementamos que veio da UserDetails no user
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new UserResponse(token));

    }

    public ResponseEntity register(UserRequest data){

        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


}
