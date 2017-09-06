package com.vstumpf.lolmanager.dto;

import lombok.Data;

@Data
public class UserEditDto {
    private Long id;
    private String password;
    private String passwordConfirm;
    private String passwordOld;
}
