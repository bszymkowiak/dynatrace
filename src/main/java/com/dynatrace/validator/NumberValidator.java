package com.dynatrace.validator;

public class NumberValidator {

    public static boolean isNumberLowerOrEqual(int number){
        return (number <= 255 && number > 0) ;
    }
}
