package com.dynatrace.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

public class DateValidator {

    private static final String DATE_PATTERN = "^\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12]\\d|3[01])$";

    public static boolean isValidDate(String date) {
        if (date == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        return pattern.matcher(date).matches();
    }
}

