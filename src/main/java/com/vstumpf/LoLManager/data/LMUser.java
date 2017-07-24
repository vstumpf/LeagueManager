package com.vstumpf.LoLManager.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Vincent on 7/23/2017.
 */

@Data
@AllArgsConstructor
public class LMUser {
    private String username;
    private String email;
}
