package com.vstumpf.lolmanager.dto;

import com.vstumpf.lolmanager.model.User;
import lombok.Data;

import java.util.Date;

/**
 * Created by Vincent on 8/26/2017.
 */

@Data
public class UserOwnedDto extends UserDto {
    private Date lastPasswordResetDate;
    private String iso;

    public UserOwnedDto(User u) {
        super(u);
        lastPasswordResetDate = u.getLastPasswordResetDate();
        iso = u.getIso();
    }
}
