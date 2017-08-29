package com.vstumpf.lolmanager.validator;

/**
 * Created by Vincent on 8/28/2017.
 */
public enum ErrorCode {
    BAD_VALUE("badValue"),
    MISSING_FIELD("missingField"),
    NOT_FOUND("notFound"),
    BAD_LOGIN("badLogin"),
    PASSWORD_MISMATCH("passwordMismatch"),
    DUPLICATE_USERNAME("duplicateUsername");

    private final String text;
    ErrorCode(final String s) {
        text = s;
    }

    public String mes() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
