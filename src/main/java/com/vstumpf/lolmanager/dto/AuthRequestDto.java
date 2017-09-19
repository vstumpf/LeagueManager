package com.vstumpf.lolmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Vincent on 7/27/2017.
 */
@AllArgsConstructor
@Data
public class AuthRequestDto {

    private String username;
    private String password;

    public AuthRequestDto() {
        super();
    }


}
