package com.example.demo.rest.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private  String bearer;
    private String email;

}