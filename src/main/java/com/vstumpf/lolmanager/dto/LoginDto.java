package com.vstumpf.lolmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Vincent on 7/24/2017.
 */
@Data
@AllArgsConstructor
public class LoginDto {

    private String email;
    private String password;

}
