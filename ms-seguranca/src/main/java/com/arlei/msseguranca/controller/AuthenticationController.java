package com.arlei.msseguranca.controller;

import com.arlei.msseguranca.request.UserAuthRequest;
import com.arlei.msseguranca.request.UserRequest;
import com.arlei.msseguranca.service.AuthenticationService;
import com.arlei.msseguranca.service.AuthorizationService;
import com.arlei.msseguranca.service.TokenService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserAuthRequest data){
        return authenticationService.login(data);

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRequest data){

        return authenticationService.register(data);
    }

    // Talvez implementar um validar token... e um retorno de UserDetails


    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestHeader("Authorization") String tokenHeader) {
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Token Inválido");
        }
        String token = tokenHeader.substring(7); // Remover "Bearer " do cabeçalho


        try {
            if (StringUtils.isNotEmpty(token)) {
                //UserDetails userDetails = authorizationService.loadUserByUsername(username);
                var username = tokenService.validateToken(token);

                UserDetails usuario = authorizationService.loadUserByUsername(username);

                if (usuario.isCredentialsNonExpired())
                    return ResponseEntity.ok("Token Valido");

            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token Invalido ou Expirado");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token Inválido");
    }

}
