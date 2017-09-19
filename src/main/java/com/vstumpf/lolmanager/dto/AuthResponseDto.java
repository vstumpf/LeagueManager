package com.vstumpf.lolmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Vincent on 7/27/2017.
 */
@AllArgsConstructor
public class AuthResponseDto {
    @Getter
    private final String token;
}
