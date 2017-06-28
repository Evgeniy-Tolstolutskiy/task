package com.implemica.task1.exception;

/**
 * Created by Евгений on 22.06.2017.
 */
public class NegativeNumberException extends Exception {
    @Override
    public String getMessage() {
        return "Given number should be greater than zero.";
    }
}
