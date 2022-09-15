package com.example.demo.rest.controllers;

import java.util.Objects;

import com.example.demo.Model.Account;
import com.example.demo.rest.model.Login;
import com.example.demo.rest.model.Signup;
import com.example.demo.rest.model.User;
import com.example.demo.rest.security.jwt.JwtResponse;
import com.example.demo.rest.security.jwt.JwtTokenUtil;
import com.example.demo.services.AccountService;
import com.example.demo.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private AuthenticationService jwtInMemoryUserDetailsService;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody Login authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(authenticationRequest.getEmail(),token));
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Signup signUpRequest) throws Exception {
        // Create new user's account
        Account user = new Account();
        user.setRole("USER");
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setFirstname(signUpRequest.getFirstName());
        user.setLastname(signUpRequest.getLastName());
        accountService.save(user);
        authenticate(user.getEmail(), user.getPassword());

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(user.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token,user.getEmail()));

    }
    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
