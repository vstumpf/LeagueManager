package com.vstumpf.lolmanager.dto;

import com.vstumpf.lolmanager.validator.ErrorCode;
import lombok.Data;
import org.hibernate.validator.constraints.Email;


/**
 * Created by Vincent on 8/26/2017.
 */

@Data
public class UserRegisterDto {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String passwordConfirm;
}
