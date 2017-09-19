package com.vstumpf.lolmanager.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Created by Vincent on 8/26/2017.
 */

@Component
public class ValidatorUtils {

    private Pattern emailPattern;

    public ValidatorUtils() {
        String regex = "^\\S+@\\S+\\.\\S+$";
        emailPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    public boolean isEmail(String value) {
        return emailPattern.matcher(value).matches();
    }

    public boolean isValidPassword(String value) {
        return value.length() > 9;
    }
}
