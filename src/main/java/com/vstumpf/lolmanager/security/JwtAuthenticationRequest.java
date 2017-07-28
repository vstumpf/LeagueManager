package com.vstumpf.lolmanager.security;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Vincent on 7/27/2017.
 */
@AllArgsConstructor
@Data
public class JwtAuthenticationRequest {

    private String username;
    private String password;

    public JwtAuthenticationRequest() { super(); }


}
