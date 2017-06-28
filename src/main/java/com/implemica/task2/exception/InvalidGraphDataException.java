package com.implemica.task2.exception;

/**
 * Created by Евгений on 28.06.2017.
 */
public class InvalidGraphDataException extends Exception {
    @Override
    public String getMessage() {
        return "This graph contains one or more transportation costs greater than 200000.";
    }
}
