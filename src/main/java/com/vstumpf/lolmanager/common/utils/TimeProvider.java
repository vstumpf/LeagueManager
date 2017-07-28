package com.vstumpf.lolmanager.common.utils;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Vincent on 7/24/2017.
 */
@Component
public class TimeProvider {
    public Date now() {
        return new Date();
    }
}
