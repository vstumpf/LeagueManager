package com.vstumpf.lolmanager.common.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import java.util.regex.Matcher;
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

}
