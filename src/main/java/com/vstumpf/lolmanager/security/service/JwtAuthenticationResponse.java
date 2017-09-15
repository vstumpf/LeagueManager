package com.vstumpf.lolmanager.security.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Vincent on 7/27/2017.
 */
@AllArgsConstructor
public class JwtAuthenticationResponse {
    @Getter
    private final String token;
}
