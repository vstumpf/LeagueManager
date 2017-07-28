package com.vstumpf.lolmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Vincent on 7/24/2017.
 */
@Data
@AllArgsConstructor
public class Login {

    private String email;
    private String password;

}
